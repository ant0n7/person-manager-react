<template>
  <header
    class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom"
  >
    <a
      href="/"
      class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none"
    >
      <!-- <svg
        class="bi me-2"
        width="40"
        height="32"
        role="img"
        aria-label="Bootstrap"
      >
        <use xlink:href="#bootstrap"></use>
      </svg> -->
      <img src="/logo_books.png" height="32" alt="Logo">
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><NuxtLink to="/" class="nav-link px-2 link-dark">Home</NuxtLink></li>
      <li><NuxtLink to="/students" class="nav-link px-2 link-dark">Students</NuxtLink></li>
      <li><NuxtLink to="/subjects" class="nav-link px-2 link-dark">Subjects</NuxtLink></li>
    </ul>

    <div class="col-md-4 text-end">
      <button type="button" class="btn btn-outline-primary me-2">Login</button>
      <button type="button" class="btn btn-primary me-2">Create Student</button>
      <button type="button" class="btn btn-primary">Create Subject</button>
    </div>
  </header>
</template>
