<template>
    <section class="sign-in-page">
          <div id="container-inside">
              <div id="circle-small"></div>
              <div id="circle-medium"></div>
              <div id="circle-large"></div>
              <div id="circle-xlarge"></div>
              <div id="circle-xxlarge"></div>
          </div>
          <div class="container p-0">
              <div class="row no-gutters">
                  <div class="col-md-6 text-center pt-5">
                      <div class="sign-in-detail text-white">
                          <lantern></lantern>
                      </div>
                  </div>
                  <managerlogin></managerlogin>
              </div>
          </div>
      </section>
  </template>
  
  <script>
  import lantern from '@/components/Datashow/lantern.vue';
  import managerlogin from '@/components/Authentication/managerlogin.vue';
    export default {
        name:"mlogin",
        components:{
            managerlogin,
            lantern
        }
    }
  </script>