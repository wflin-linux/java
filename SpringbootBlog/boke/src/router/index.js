import {
	createRouter,
	createWebHistory
} from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Home from '../components/Home.vue'
import BlogCategory from '../views/BlogCategory.vue'
import Echarts from '../views/Echarts.vue'
import User from '../views/User.vue'
import AddBlog from '../views/AddBlog.vue'

const routes = [
	{	path: '/',	redirect: '/login'},
	{	path: '/login',
		name: 'Login',
		component: Login
	},
	{
		path: '/register',
		name: 'Register',
		component: Register
	},
	{
		path: '/home',
		component: Home,
		redirect:'/echarts',
		children:[
			{
				path: '/blogCategory',
				name: 'BlogCategory',
				component: BlogCategory
			},
			{
				path: '/echarts',
				name: 'Echarts',
				component: Echarts
			},
			{
				path: '/user',
				name: 'User',
				component: User
			},
			{
				path: '/addBlog',
				name: 'AddBlog',
				component: AddBlog
			}	
		]
	}
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

// ；路由导航守卫	
// router.beforeEach((to,from,next)=>{
// 	if(to.path=='/login' || to.path=='/'|| to.path=='/register' ){
// 		return next()
// 		const tokenStr = window.sessionStorage.getItem('token')	
// 	}
// 	if(tokenStr==null) 
// 		return next('/login')
// 	this.next()
// })
export default router
