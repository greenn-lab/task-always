import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import axios from 'axios'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')

axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json;charset=utf-8'
axios.interceptors.response.use(res => res, err => {
  console.log(err)
  return err /*Promise.reject(err)*/
})
