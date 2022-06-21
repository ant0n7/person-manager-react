<template>
  <component :is="tag" :class="customClass">
    <slot />
    <NuxtLink
      v-if="buttonText"
      class="btn btn-primary btn-md mb-2 ms-auto float-end"
      type="button"
      :to="buttonLink"
      >{{ buttonText }}</NuxtLink
    >
  </component>
</template>

<script>
export default {
  props: {
    buttonText: String,
    buttonLink: String,
    customClass: String,
    tag: {
      type: String,
      default: "h1",
    },
  },
};
</script>
