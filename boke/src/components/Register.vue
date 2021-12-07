<template>
	<div class="login_container">
		<div class="login_box">
			<div style="text-align: center;">
				<h1>Register</h1>
				<img src="../assets/logo.png">
			</div>

			<el-form :model="loginForm" style="text-align: center;">
				<!-- 邮箱 -->
				<el-form-item>
					<el-input class="login_form" v-model="loginForm.name" placeholder="昵称" :plain="true"></el-input>
				</el-form-item>

				<el-form-item>
					<el-input class="login_form" v-model="loginForm.email" placeholder="邮箱"></el-input>
					<el-button type="primary" style="margin-left: -10px;" @click="sendMessage"
						:plain="true">发送验证码</el-button>
				</el-form-item>

				<!-- 验证码 -->
				<el-form-item>
					<el-input class="login_form" v-model="loginForm.code" placeholder="验证码" :plain="true"></el-input>
				</el-form-item>

				<!-- 按钮 -->
				<el-form-item>
					<el-button type="primary" @click="toregister">注册</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script type="text/javascript">
	import {
		defineComponent
	} from 'vue'
	import {
		ElMessage
	} from 'element-plus'
	import axios from 'axios'
	export default {
		data() {
			return {
				loginForm: {
					email: '',
					code: ''
				},
			};
		},
		methods: {
			sendMessage() {
				var email = this.loginForm.email
				if (email == "") {
					ElMessage({
						showClose: true,
						message: 'email 不能为空',
						type: 'warning',
					})
					return;
				}
				var email_reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
				if (!email_reg.test(email)) {
					ElMessage({
						showClose: true,
						message: '请输入正确的邮箱地址',
						type: 'warning',
					})
					return;
				};
				axios.get('http://127.0.0.1:8088/getCode', {
					params: {
						email: email,
						status: 1
					}
				}).then(res => {
					var message = res.data.message;
					if (message == "验证码发送成功") {
						ElMessage({
							showClose: true,
							message: message,
							type: 'success',
						})
					} else {
						ElMessage({
							showClose: true,
							message: message,
							type: 'warning',
						})
					}

				})
			},
			toregister() {
				var email = this.loginForm.email;
				var code = this.loginForm.code;
				var name = this.loginForm.name
				if (name == "") {
					ElMessage({
						showClose: true,
						message: "昵称不能为空",
						type: 'warning',
					})
					return;
				}

				if (email == "") {
					ElMessage({
						showClose: true,
						message: "邮箱不能为空",
						type: 'warning',
					})
					return;
				}
				var email_reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
				if (!email_reg.test(email)) {
					ElMessage({
						showClose: true,
						message: "请输入正确的邮箱地址",
						type: 'warning',
					})
					return;
				}
				if (code == "") {
					ElMessage({
						showClose: true,
						message: "验证码不能为空",
						type: 'warning',
					})
					return;
				};
				axios.get('http://127.0.0.1:8088/toregister', {
					params: {
						name: name,
						email: email,
						code: code,
					}
				}).then(res => {
					var message = res.data.message
					ElMessage({
						showClose: true,
						message: message,
						type: 'success',
					})
					this.$router.push('/')
				})
			}

		},
	}
</script>

<style type="text/css" scoped>
	.login_container {
		background-color: skyblue;
		height: 100%;
	}

	.login_box {
		width: 450px;
		height: 450px;
		background-color: #fff;
		border-radius: 3px;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);

		.avatar_box {}
	}

	.login_form {
		float: left;
		bottom: 0;
		width: 75%;
		padding: 0 20px;
		margin-right: 0;
		box-sizing: border-box;
	}
</style>
