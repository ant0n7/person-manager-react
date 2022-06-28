<script setup>
// const username = useCookie("username").value;
// const password = useCookie("password").value;
// const role = useCookie("role").value;
const username = useUsername();
const password = usePassword();
const role = useRole();
</script>

<template>
  <div>
    <div class="container">
      <Navbar />
    </div>
    <div v-if="useUsername().value && usePassword().value && useRole().value">
      <slot />
    </div>

    <div v-else>
      <div class="container">
        <div class="row">
          <div class="col-md-4"></div>
          <div class="col-md-4 col-12">
            <LoginForm />
          </div>
          <div class="col-md-4"></div>
        </div>
      </div>
    </div>
  </div>
</template>