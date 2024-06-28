<template>
  <div class="col-md-6 bg-white pt-5">
      <div class="sign-in-from">
          <h1 class="mb-0">注册</h1>
          <p>请输入您注册的账号和密码</p>
          <form class="mt-4">
              <div class="form-group">
                  <label for="exampleInputAccount">账号</label>
                  <input type="text" class="form-control mb-0" id="exampleInputAccount" placeholder="请输入账号">
              </div>
              <div class="form-group">
                  <label for="exampleInputPassword1">密码</label>
                  <input type="password" class="form-control mb-0" id="exampleInputPassword1" placeholder="请输入密码">
              </div>
              <div class="form-group">
                  <label for="exampleInputPassword2">确认密码</label>
                  <input type="password" class="form-control mb-0" id="exampleInputPassword2" placeholder="请确认密码">
              </div>
              <div class="form-group">
                  <label for="exampleInputEmail">邮箱</label>
                  <input type="email" class="form-control mb-0" id="exampleInputEmail" placeholder="请输入邮箱">
              </div>
              
              <div class="form-group">
                  <label for="exampleInputVaild">验证码</label>
                  <input type="text" class="form-control mb-0" id="exampleInputVaild" placeholder="请输入验证码">
              </div>

              <div class="d-inline-block w-100">
                  <div class="custom-control custom-checkbox d-inline-block mt-2 pt-1">
                      <input type="checkbox" class="custom-control-input" id="customCheck1">
                      <label class="custom-control-label" for="customCheck1">我同意注册</label>
                  </div>
                  <button @click="submit" class="btn btn-primary float-right">注册</button>
              </div>
          </form>
      </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'

// do not use same name with ref
const form = reactive({
  exampleInputAccount: '',
  exampleInputPassword1: '',
  exampleInputPassword2: '',
  exampleInputEmail: '',
  exampleInputVaild:''
})

const submit = () => {
  console.log('submit!')
}
</script>
