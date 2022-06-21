<template>
  <div class="card">
    <NuxtLink :to="link" class="link-unstyled">
      <div class="card-body">
        <h5
          class="card-title"
          :class="!subtitle && !buttonText && !image ? 'mb-0' : ''"
        >{{ title }}</h5>
        <h6
          v-if="subtitle"
          class="card-subtitle mb-2 text-muted"
        >
          {{ subtitle }}
        </h6>
        <img v-if="image" class="card-img" :src="image" alt="Logo" />
        <p v-if="buttonText" class="card-text">
          <NuxtLink v-if="buttonText" :to="link" class="btn btn-primary mt-2">
            {{ buttonText }}
          </NuxtLink>
        </p>
      </div>
    </NuxtLink>
  </div>
</template>

<script>
export default {
  props: {
    title: String,
    subtitle: String,
    buttonText: String,
    link: String,
    image: String,
  },
};
</script>
