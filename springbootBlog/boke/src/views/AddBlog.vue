<template>
	<el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm"
		style="width:500px">
		<el-form-item label="作者" prop="author">
			<el-input v-model="ruleForm.author"></el-input>
		</el-form-item>

		<el-form-item label="标题" prop="title">
			<el-input v-model="ruleForm.title"></el-input>
		</el-form-item>
		
		<el-form-item label="内容" prop="content">
			<el-input v-model="ruleForm.content"></el-input>
		</el-form-item>
		
		<el-form-item label="点赞" prop="star">
			<el-input v-model="ruleForm.star"></el-input>
		</el-form-item>
		
		<el-form-item label="分享" prop="share">
			<el-input v-model="ruleForm.share"></el-input>
		</el-form-item>

		<el-form-item label="tags" prop="tags">
			<el-input v-model="ruleForm.tags"></el-input>
		</el-form-item>

		<el-form-item label="日期" required>
			<el-col :span="11">
				<el-form-item prop="date1">
					<el-date-picker v-model="ruleForm.date1" type="date" placeholder="Pick a date" style="width: 100%">
					</el-date-picker>
				</el-form-item>
			</el-col>
		</el-form-item>

		<el-form-item>
			<el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
			<el-button @click="resetForm('ruleForm')">Reset</el-button>
		</el-form-item>
	</el-form>
</template>
<script>
	import { ElMessage } from 'element-plus'
	import axios from 'axios'
	axios.defaults.withCredentials = true
	export default {
		created() {
			// console.log(this.$route.params)
			this.ruleForm=this.$route.params
		},
		data() {
			return {
				ruleForm: {
					author: 'ww',
					title: 'tt',
					content:'22',
					share:'33',
					star:'22',
					tags: '44',
					date1: '2021-2-1',
				},
				rules: {
					author: [{
						required: true,
						message: '',
					}, ],
					content: [{
						required: true,
						message: '',
					}, ],
					title: [{
						required: true,
						message: '',
					}, ],
					date1: [{
						type: 'date',
						required: true,
						message: 'Please pick a date',
						// trigger: 'blur',
					}, ],
				},
			}
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						// alert('submit!'),
						if(this.$route.params.methord=='blogm'){
							axios.post('http://127.0.0.1:8088/Blog/save/',{
								id:this.$route.params.id,
							    author: this.ruleForm.author,
							    date: this.ruleForm.date1,
							    title: this.ruleForm.title,
							    content: this.ruleForm.content,
							    star: this.ruleForm.star,
							    share: this.ruleForm.share,
							    tags: this.ruleForm.tags
							}).then(res=>{
								ElMessage({
								        showClose: true,
								        message: res.data.message,
								        type: 'success',
								      })
							})
						}else{
							axios.post('http://127.0.0.1:8088/Blog/save/',{
							    author: this.ruleForm.author,
							    date: this.ruleForm.date1,
							    title: this.ruleForm.title,
							    content: this.ruleForm.content,
							    star: this.ruleForm.star,
							    share: this.ruleForm.share,
							    tags: this.ruleForm.tags
							}).then(res=>{
								ElMessage({
								        showClose: true,
								        message: res.data.message,
								        type: 'success',
								      })
							})
						}
					} else {
						ElMessage({
						        showClose: true,
						        message: 'error submit!!',
						        type: 'waring',
						      })
						return false
					}
				})
			},
			resetForm(formName) {
				this.$refs[formName].resetFields()
			},
		},
	}
</script>
