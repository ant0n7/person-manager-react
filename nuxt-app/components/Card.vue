<template>
  <div class="card">
    <NuxtLink :to="link" class="link-unstyled">
      <div class="card-body">
        <h5 class="card-title">{{ title }}</h5>
        <h6 class="card-subtitle mb-2 text-muted">{{ subtitle }}</h6>
        <img class="card-img" :src="image" alt="Logo" />
        <p class="card-text">
          <NuxtLink :to="link" class="btn btn-primary mt-2">
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
    image: String
  }
}
</script>