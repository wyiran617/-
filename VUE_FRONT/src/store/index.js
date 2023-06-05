import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import shoppingCart from './modules/shoppingCart'

Vue.use(Vuex)

export default new Vuex.Store({
   state: {
        currentPathName: ''
    },
   mutations: {
        setPath(state) {
          state.currentPathName = localStorage.getItem("currentPathName")
        }
    },
  strict:true,
  modules: {
    user,
    shoppingCart
  }
})
