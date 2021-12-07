<template>
	<div id="">
		<el-input v-model="input" placeholder="Please input" style="width: 500px;" id="findinfo" />
		<el-button type="primary" @click.prevent="search()">Search</el-button>
	</div>
	<el-table :data="tableData" style="width: 100%">
		<el-table-column fixed prop="date" label="时间" width="160" />
		<el-table-column prop="author" label="作者" width="120" />
		<el-table-column prop="title" label="标题" width="120" />
		<el-table-column prop="content" label="内容" width="120" />
		<el-table-column prop="star" label="点赞" width="120" />
		<el-table-column prop="share" label="分享" width="120" />
		<el-table-column fixed="tag" label="类" width="120" />
		<el-table-column fixed="right" label="Operations" width="200">
			<template #default="scope">
				<el-button :plain="true" type="danger" size="small" @click.prevent="deleteRow(scope.$index, tableData)">
					delete</el-button>

				<el-button size="small" @click="editRow(scope.$index, scope.row)" type="danger">Edit</el-button>
			</template>
		</el-table-column>
	</el-table>
	<el-pagination background layout="prev, pager, next" :total='pagemax' style="text-align: center;"
		@current-change="page" v-show="boolean"></el-pagination>
</template>



<script>
	import axios from 'axios'
	import {
		ElMessage
	} from 'element-plus'
	import {
		defineComponent,
		ref
	} from 'vue'
	// axios.default.baseURL='http://127.0.0.1:8088'

	export default {
		setup() {
			return {
				input: ref(''),
			}
		},
		data() {
			return {
				pagemax: 10,
				boolean: true,
				tableData: [{
					date: '',
					author: '',
					title: '',
					content: '',
					star: '',
					share: '',
					tag: '',
				}, ],
			}
		},
		created() {
			axios.get('http://127.0.0.1:8088/Blog/findallBlogSubPages/1').then(res => {
				this.tableData = res.data.data.content;
				this.pagemax = (Math.ceil((res.data.data.totalElements) / (res.data.data.size)) * 10)
			})
		},
		methods: {
			deleteRow(index, rows) {
				let Id = JSON.parse(JSON.stringify(rows.splice(index, 1)))[0].id
				axios.get('http://127.0.0.1:8088/Blog/del/', {
					params: {
						id: Id
					}
				}).then(function(response) {
					// 处理成功情况
					ElMessage({
						showClose: true,
						message: response.data.message,
						type: 'success',
					})
				})
			},
			editRow(index, row) {
				// console.log(row.id)
				// console.log(index, row)
				this.$router.push({
					name:'AddBlog',
					params:{
						id:row.id,
						date:row.date,
						author:row.author,
						title:row.title,
						content:row.content,
						star:row.star,
						share:row.share,
						tag:row.tag,
						methord:"blogm",
					}})
			},
			search() {
				var findinfo = document.getElementById('findinfo');
				if (findinfo.value == '') {
					ElMessage({
						showClose: true,
						message: '搜索关键字不能为空',
						type: 'warning',
					})
					return;
				};
				axios.get('http://127.0.0.1:8088/Blog/findBlogsByParam/', {
					params: {
						Param: findinfo.value,
					}
				}).then(res => {
					this.tableData = res.data.data
					this.boolean = false
					if (res.data.message == '查询结束') {
						ElMessage({
							showClose: true,
							message: res.data.message,
							type: 'success',
						})
					} else {
						ElMessage({
							showClose: true,
							message: res.data.message,
							type: 'warning',
						})
					}
				})
			},
			page(i) {
				axios.get('http://127.0.0.1:8088/Blog/findallBlogSubPages/' + i).then(res => {
					this.tableData = res.data.data.content;
				})
			}

		},
	}
</script>

<style>
</style>
