<template>
  <header
    class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom"
  >
    <NuxtLink
      href="/"
      class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none"
    >
      <img src="/logo_books.png" height="32" alt="Logo">
    </NuxtLink>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><NuxtLink to="/" class="nav-link px-3">Home</NuxtLink></li>
      <li><NuxtLink to="/students" class="nav-link px-3">Students</NuxtLink></li>
      <li><NuxtLink to="/subjects" class="nav-link px-3" >Subjects</NuxtLink></li>
      <li><NuxtLink to="/classes" class="nav-link px-3">Classes</NuxtLink></li>
    </ul>

    <div class="col-md-4 text-end">
      <NuxtLink to="/login" type="button" class="btn btn-outline-primary">Login</NuxtLink>
    </div>
  </header>
</template>
<style scoped>
  li{
    font-size: 20px;
  }
  .nav-link{
    color: #757575
  }
</style>