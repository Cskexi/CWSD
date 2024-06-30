<template>
  <div class="about">
    <h1>This is an about page</h1><br>
   {{ this.$route.query.type }}--{{ this.$route.query.school }}
    <br>
    <Chapter2></Chapter2>
  </div>
</template>


<script>
// @ is an alias to /src
import Chapter2 from '@/components/Chapter2.vue'

export default {
  name: 'AbourView',
  components: {
    Chapter2
  }
}
</script>