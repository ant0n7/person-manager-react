<script setup>
const username = useCookie('username').value ?? 'default';
const password = useCookie('password').value ?? 'default';
const role = useCookie('role').value ?? 'default';

const base64auth = btoa(`${username}:${password}`);
const { pending, data: classes } = await useAsyncData('classes', () => $fetch('http://localhost:8080/api/classes/', {
  headers: {
    Authorization: `Basic ${base64auth}`,
  },
}));
</script>

<template>
  <div class="container">
    <Heading buttonText="Create" buttonLink="/classes/create" :role="role">Classes</Heading>

    <div v-if="pending">
      <Alert type="info">
        Loading... {{ pending }}
      </Alert>
    </div>

    <div class="row" v-else>
      <div
        class="col-lg-3 col-md-4 col-sm-6 col-12 p-2"
        v-for="appClass in classes"
        :key="appClass.id"
      >
        <Card
          :title="appClass.classname"
          :link="`/classes/${appClass.id}`"
          />
      </div>
    </div>

    <div class="row" v-if="!classes">
      <div class="col-12">
        <Alert type="warning" warning-icon>
          No classes found.  Make sure you are logged in!
        </Alert>
      </div>
    </div>
  </div>
</template>

