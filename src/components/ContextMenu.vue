<!-- ContextMenu.vue -->
<template>
  <el-card
      class="context-menu"
      v-if="visible"
      :style="{ top: `${position.y}px`, left: `${position.x}px`, position: 'absolute' }"
      @contextlost="hideMenu"
  >
    <slot/>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      position: {x: 0, y: 0}
    };
  },
  methods: {
    showMenu(event) {
      this.position = {x: event.clientX, y: event.clientY};
      this.visible = true;
    },
    hideMenu() {
      this.visible = false;
    },
    handleCommand(command) {
      this.$emit('command', command);
      this.hideMenu();
    }
  }
};
</script>

<style scoped>
.context-menu {
  background-color: #fff4;
  z-index: 9999;
}
</style>
