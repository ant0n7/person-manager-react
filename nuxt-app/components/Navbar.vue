<script setup>
const username = useUsername();
const password = usePassword();
const role = useRole();
</script>

<template>
  <header
    class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom"
  >
    <NuxtLink
      href="/"
      class="d-flex align-items-center col-md-2 mb-2 mb-md-0 text-dark text-decoration-none"
    >
    <i class="fa fa-users fa-xl" aria-hidden="true"></i>
    </NuxtLink>

    <ul class="nav col-md-8 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><NuxtLink to="/" class="nav-link px-3">Home</NuxtLink></li>
      <li><NuxtLink to="/students" class="nav-link px-3">People</NuxtLink></li>
      <li><NuxtLink to="/subjects" class="nav-link px-3">Subjects</NuxtLink></li>
      <li><NuxtLink to="/classes" class="nav-link px-3">Classes</NuxtLink></li>
    </ul>

    <div class="col-md-2 text-end">
      <NuxtLink v-if="!username && !password && !role" to="/login" type="button" class="btn btn-outline-primary">Login</NuxtLink>
      <button v-else @click="logout" type="button" class="btn btn-outline-danger">Logout</button>
    </div>
  </header>
</template>

<style scoped>
  .nav-link{
    color: #757575
  }
</style>

<script>
export default {
  methods: {
    logout() {
      useCookie("username").value = undefined;
      useUsername().value = undefined;
      useCookie("password").value = undefined;
      usePassword().value = undefined;
      useCookie("role").value = undefined;
      useRole().value = undefined;
    },
  }
}
</script>