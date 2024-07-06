import {ref} from "vue";

const light = {
  background: {
    color: 'white',

  },
  opacity: {
    L1: 0.8,
    L2: 0.6,
    L3: 0.4,
    L4: 0.2,
  }
}

const custom = {

}

const themes = {
  light, custom
}

const theme = ref(light)

export default theme
