import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'
import './store/css/global.css'

createApp(App).use(store).use(ElementPlus).use(router).mount('#app')
