<template>
  <el-container>
    <el-header>
      <h1>{{ $t('setting.title') }}</h1>
    </el-header>
    <el-main>
      <el-tabs v-model="activeTab">
        <!-- My Account Tab -->
        <el-tab-pane :label=" $t('setting.my_account') " name="account">
          <el-card>
            <el-row>
              <el-col :span="8">
                <img src="https://via.placeholder.com/150" alt="Profile Picture"/>
              </el-col>
              <el-col :span="16">
                <h2>{{ user.name }}</h2>
                <p>Email: {{ user.email }}</p>
                <el-button type="primary" @click="editAccount">{{ $t('setting.edit') }}</el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-tab-pane>

        <!-- Theme and Background Settings Tab -->
        <el-tab-pane :label="$t('setting.theme_and_background')" name="theme">
          <el-form>
            <el-form-item :label="$t('setting.theme_color')">
              <el-color-picker v-model="themeColor"></el-color-picker>
            </el-form-item>
            <el-form-item :label="$t('setting.background_image')">
              <el-upload
                  action="#"
                  list-type="picture-card"
                  :on-preview="handlePictureCardPreview"
                  :on-remove="handleRemove"
              >
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog>
                <img width="100%" :src="dialogImageUrl" alt=""/>
              </el-dialog>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <!-- Language Settings Tab -->
        <el-tab-pane :label="$t('setting.language')" name="language">
          <el-radio-group v-model="language">
            <el-radio-button :value="'en'" :label="$t('setting.English')"></el-radio-button>
            <el-radio-button :value="'zh'" :label="$t('setting.Chinese')"></el-radio-button>
            <el-radio-button :value="'jp'" :label="$t('setting.Japanese')"></el-radio-button>
            <!-- Add more languages as needed -->
          </el-radio-group>
        </el-tab-pane>

        <!-- Logout Tab -->
        <el-tab-pane :label="$t('setting.logout')" name="logout">
          <el-button type="danger" @click="logout">{{ $t('setting.logout') }}</el-button>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
import UserLayout from "@/components/CrLayout.vue";

export default {
  data() {
    return {
      activeTab: 'account',
      user: {
        name: 'John Doe',
        email: 'john.doe@example.com'
      },
      themeColor: '#409EFF',
      language: 'English',
      dialogImageUrl: '',
      dialogVisible: false,
    };
  },
  methods: {
    editAccount() {
      // Implement account editing logic here
      console.log('Edit Account');
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    logout() {
      // Implement logout logic here
      console.log('Logout');
    }
  },
  components: {
    UserLayout
  }
};
</script>

<style scoped>
h1 {
  margin: 0;
  padding: 20px;
  color: #333;
}

.el-card {
  margin: 20px 0;
}

.el-row {
  align-items: center;
}

.el-col img {
  width: 100%;
  border-radius: 50%;
}
</style>
