import Vue from 'vue'
import App from './App.vue'
import router from "./router/index";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

Vue.config.productionTip = false
axios.defaults.baseURL = "http://localhost:8080/webapp/webapi/"

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
