<template>
  <component :is="tag" :class="customClass">
    <slot />
    <NuxtLink
      v-if="buttonText && buttonLink"
      class="btn btn-primary btn-md mb-2 ms-auto float-end"
      type="button"
      :to="buttonLink"
      >{{ buttonText }}</NuxtLink
    >
    <button
      v-if="buttonText && buttonAction"
      class="btn btn-primary btn-md mb-2 ms-auto float-end"
      type="button"
      @click="buttonAction"
      >{{ buttonText }}</button
    >
  </component>
</template>

<script>
export default {
  props: {
    buttonText: String,
    buttonLink: String,
    buttonAction: () => {},
    customClass: String,
    tag: {
      type: String,
      default: "h1",
    },
  },
};
</script>
