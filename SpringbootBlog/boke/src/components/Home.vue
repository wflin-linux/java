<template>
	<el-container>

		<!-- 头 -->
		<el-header>
			<div>
				<img src="../assets/logo.png" alt="" >
				<span>博客后台管理系统</span>
			</div>
			<!-- <el-button type="info">退出</el-button> -->
		</el-header>
		<!-- 分割线 -->
		<el-divider></el-divider>
		<!-- 大主体 -->
		<el-container>

			<!-- 侧边 -->
			<el-aside width="200px">
				<el-menu router default-active="" class="el-menu-vertical-demo" :collapse="isCollapse"
					@open="handleOpen" @close="handleClose">
					<el-menu-item index="/echarts">
						<span>数据分析</span>
					</el-menu-item>

					<el-menu-item index="/blogCategory">
						<span>博客管理</span>
					</el-menu-item>
					
					<el-menu-item index="/addBlog">
						<span>添加博文</span>
					</el-menu-item>
					
					<el-menu-item index="/user">
						<span>用户管理</span>
					</el-menu-item>
				</el-menu>
			</el-aside>

			<!-- 主体 -->
			<el-container>

				<!-- 真主体 -->
				<el-main>
					<router-view></router-view>
				</el-main>

				<!-- 尾部 -->
			</el-container>
		</el-container>
	</el-container>
</template>

<script type="text/javascript">

</script>
<style>
	.el-container {
		height: 100%;
	}

	.el-header {
		/* background-color: #53A8FF; */
		height: 15%;
		display: flex;
		padding-left: 2px;
		font-size: 40px;
	}
	img {
		width: 100px;
		height: 100px;
	}

	.el-aside {
		/* background-color: #555555 */
	}

	.el-container {
		/* background-color: #67C23A; */
	}

	.el-footer {
		/* background-color: #111111; */
	}
</style>
