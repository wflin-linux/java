<template>
	<el-row style="height: 50%;">
		<el-col :span="12"><Pan></Pan></el-col>
		<el-col :span="12"><Circle></Circle></el-col>	
	</el-row>
	<el-row style="height: 50%;">
		<el-col :span="12"><Bar></Bar></el-col>
		<el-col :span="12"><Gif></Gif></el-col>
	</el-row>
</template>

<script type="text/javascript">
	import Pan from '../store/datasys/pan.vue'
	import Circle from '../store/datasys/circle.vue'
	import Bar from '../store/datasys/bar.vue'
	import Gif from '../store/datasys/gif.vue'
	export default{
		components:{
			Pan,
			Circle,
			Bar,
			Gif,
		}
	}
</script>