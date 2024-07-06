<template>
  <el-card class="profile" id="MainProfile">
    <div class="background" :style="{ backgroundImage: `url(${profileData.backgroundImage})` }">
      <el-avatar
          class="profile-avatar"
          :src="profileData.avatar_url"
          @mouseover="isHover = true"
          @mouseleave="isHover = false"
          :class="{'hover-image':isHover}"
      />
    </div>

    <div class="userinfo">
      <div class="name-id-container" style="display: grid;">
        <el-text class="cr-title user-name">{{ profileData.username }}</el-text>
        <el-text class="cr-title user-id">@{{ profileData.accountNum }}</el-text>
      </div>

<!--      <div class="edit-icon" @click="modifyProfileDialogVisible = true; ElMessage.warning('open update')">
        <el-icon size="25" class="icon-hover">
          <Edit/>
        </el-icon>
      </div>-->
      <update-profile-dialog></update-profile-dialog>

      <el-descriptions class="profile-descriptions">
        <el-descriptions-item :label="$t('profile.gender')">{{ profileData.sex }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.email')">{{ profileData.mail }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.address')">{{ profileData.location }}</el-descriptions-item>
        <el-descriptions-item label="生日">{{ profileData.birthday }}</el-descriptions-item>
        <el-descriptions-item label="注册时间">{{ profileData.createTime || 'NULL' }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="additional-info">
      <el-card class="signature-card">
        <h3>{{ $t('profile.signature') }}</h3>
        <p>{{ profileData.signature }}</p>
      </el-card>
    </div>
  </el-card>

</template>

<script setup>
import {DefaultProfileData, modifyProfileDialogVisible} from "@/store/cr_config.js";
import UpdateProfileDialog from './UpdateProfileDialog.vue'
import {ref} from "vue";
import {Edit} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

const isHover = ref(false)


defineProps({
  profileData: {
    type: Object,
    default: DefaultProfileData()
  }
})

</script>

<style scoped>
.profile {
  text-align: center;
  height: calc(100vh - var(--cr-header-height));
  padding: 2px 2px;
  background-color: #fff1;
}

#MainProfile {
  border-style: none;
}

.background {
  position: relative;
  width: 100%;
  height: 40vh;
  border-radius: 0.5rem 0.5rem 0 0;
  border-bottom-style: none;
  background-size: cover;
  background-position: center;

  margin-bottom: 5px;
  box-shadow: 0 0 1em var(--cr-static-light-color);
}

.profile-avatar {
  position: absolute;
  bottom: -50px;
  left: 50%;
  transform: translateX(-50%);
  width: 150px;
  height: 150px;
  border-radius: 50%;
  border: 3px solid #fffefe;
  box-shadow: 0 0 2em #76e0d8;

}

.profile-avatar:hover  {
  box-shadow: 0 0 2em var(--cr-hot-hover-color);
  border: 3px solid var(--cr-hot-hover-color);
}

.userinfo {
  margin-top: 40px;
}

.name-id-container {
  display: grid;
  place-content: center;
  position: relative;
  bottom: 2.2rem;
}

.user-name{
  font-size: 1rem;
  font-weight: bold;
  color: transparent;
  -webkit-text-stroke: 1px #ffef;
  text-shadow: 0 0 0 #000F;
}

.user-id {
  font-size: 1rem;
  --el-text-color: white;
  font-family: Arial, Helvetica, sans-serif;
}

.profile-descriptions > * {
  background-color: transparent;
  padding: 3px;
  border-radius: 3px;
  background-image: var(--cr-gradient-color);
}

.additional-info {
  margin-top: 15px;
  text-align: left;
}

.signature-card {
  background-color: #0001;
  background-image: var(--cr-gradient-color-light);
}

.additional-info h3 {
  margin-bottom: 5px;
}

.additional-info p {
  margin-bottom: 20px;
}

.edit-icon {
  margin: 10px; /* 可以根据需要调整图标和文本之间的间距 */
  text-align: right;
  align-items: center;
}

h2 {
  text-align: center;
  margin: 0%;
}

.edit-icon:hover {
  color: var(--cr-basic-hover-color);
}
</style>
