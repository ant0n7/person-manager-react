<template>
  <h1>
    <slot />
    <NuxtLink
      v-if="buttonText"
      class="btn btn-primary btn-md mb-2 ms-auto float-end"
      type="button"
      :to="buttonLink"
      >{{ buttonText }}</NuxtLink
    >
  </h1>
</template>

<script>
export default {
  props: {
    buttonText: String,
    buttonLink: String,
  },
};
</script>
