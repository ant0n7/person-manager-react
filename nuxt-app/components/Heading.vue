<template>
  <component :is="tag" :class="customClass">
    <slot />
    <NuxtLink
      v-if="buttonText && buttonLink && role == 'TEACHER'"
      class="btn btn-primary btn-md mb-2 ms-auto float-end"
      type="button"
      :to="buttonLink"
      >{{ buttonText }}</NuxtLink
    >
    <button
      v-if="buttonText && buttonAction && role == 'TEACHER'"
      class="btn btn-primary btn-md mb-2 ms-auto float-end"
      :class="buttonType"
      type="button"
      @click="buttonAction"
      >{{ buttonText }}</button
    >
  </component>
</template>

<script>

export default {
  props: {
    role: String,
    buttonType: String,
    buttonText: String,
    buttonLink: String,
    buttonAction: Function,
    customClass: String,
    tag: {
      type: String,
      default: "h1",
    },
  },
};
</script>
