<template>
    <el-button plain @click="trueEdit" >
      Open a Table nested Dialog
    </el-button>
    <el-dialog v-model="isEdit" title="Shipping address" width="600px" @close="falseEdit">
      <el-form :model="form">
        <el-form-item label="Promotion name" label-width="140px">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Zones" label-width="140px">
          <el-select v-model="form.region" placeholder="Please select a zone">
            <el-option label="Zone No.1" value="shanghai" />
            <el-option label="Zone No.2" value="beijing" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="falseEdit">Cancel</el-button>
          <el-button type="primary" @click="falseEdit">Confirm</el-button>
        </div>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
  import { reactive, computed } from 'vue';
  import { useStore } from 'vuex';
  
  const store = useStore();
  
  // 获取 Vuex store 中的 isEdit 状态
  const isEdit = computed(() => store.state.myinfos.isEdit);

  // 改变 isEdit 的值为 true
  const trueEdit = () => {
    store.dispatch('myinfos/updateIsEdit', true);
  };
  
  // 改变 isEdit 的值为 false
  const falseEdit = () => {
    store.dispatch('myinfos/updateIsEdit', false);
  };

  // 表单数据
  const form = reactive({
    name: '',
    region: '',
    date1: '',
    date2: '',
    delivery: false,
    type: [],
    resource: '',
    desc: '',
  });
  </script>
  