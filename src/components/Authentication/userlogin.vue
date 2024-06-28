<template>
    <div class="col-md-6 bg-white pt-5">
        <div class="sign-in-from">
            <h1 class="mb-0">登录</h1>
            <p>请输入您的账号和密码</p>
            <form class="mt-4">
                <div class="form-group">
                    <label for="exampleInputAccount">账号</label>
                    <input type="text" class="form-control mb-0" id="exampleInputAccount" placeholder="请输入账号">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword">密码</label>
                    <input type="password" class="form-control mb-0" id="exampleInputPassword" placeholder="请输入密码">
                </div>
                
                <div class="form-group">
                  <label for="exampleInputVaild">验证码</label>
                  <input type="text" class="form-control" id="exampleInputVaild" placeholder="请输入验证码">
                  
                </div>
                <div class="d-inline-block w-100">
                    <button @click="submit" class="btn btn-primary float-right">登录</button>
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
    exampleInputPassword: '',
    exampleInputVaild:''
  })
  
  const submit = () => {
    console.log('submit!')
  }
  </script>
  