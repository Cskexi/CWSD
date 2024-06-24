<template>
  <div class="video-player">
    <video v-if="video.streamUrl" :src="video.streamUrl" width="100%" height="100%" controls></video>
  </div>
</template>
  
<script>
export default {
  name: 'VideoPlayer',
  props: {
    video: {
      type: Object,
      required: true,
    },
  },
};
</script>
  
<style scoped>
.video-player {
  width: 100%;
  height: 100%;
  /* 根据需要调整视频容器的高度 */
  background-color: #000;
  /* 黑色背景，适合视频播放 */
}
</style>