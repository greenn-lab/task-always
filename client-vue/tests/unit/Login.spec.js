import Vue from 'vue'
import Login from '@/views/Login'

describe('tes' +
  '\t Login.vue', () => {
  it('로그인 페이지가 잘 나올껄', () => {
    const View = Vue.extend(Login)
    const vm = new View().$mount()

    expect(vm.$el.querySelector('h1').textContent)
      .toEqual('Task Always')
  })
})
