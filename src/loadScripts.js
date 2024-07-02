const scripts = [
    './assets/js/jquery.min.js',
    './assets/js/popper.min.js',
    './assets/js/bootstrap.min.js',
    './assets/js/jquery.appear.js',
    './assets/js/countdown.min.js',
    './assets/js/waypoints.min.js',
    './assets/js/jquery.counterup.min.js',
    './assets/js/wow.min.js',
    './assets/js/apexcharts.js',
    './assets/js/lottie.js',
    './assets/js/slick.min.js',
    './assets/js/select2.min.js',
    './assets/js/owl.carousel.min.js',
    './assets/js/jquery.magnific-popup.min.js',
    './assets/js/smooth-scrollbar.js',
    './assets/js/chart-custom.js',
    './assets/js/custom.js'
  ];
  
  export function loadScripts() {
    scripts.forEach(src => {
      const script = document.createElement('script');
      script.src = src;
      script.async = false;
      document.head.appendChild(script);
    });
  }
   