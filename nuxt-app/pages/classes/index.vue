<template>
  <div class="container">
    <!-- <h1>Students <button class="btn btn-primary btn-md mb-2 ms-auto float-end" type="button">Create</button></h1> -->
    <Heading buttonText="Create" buttonLink="/classes/create">Classes</Heading>

    <div v-if="pending">
      <Alert type="info">
        Loading... {{ pending }}
      </Alert>
    </div>

    <div class="row" v-else>
      <div
        class="col-md-3 col-12 p-2"
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
          No classes found.
        </Alert>
      </div>
    </div>
  </div>
</template>

<script setup>
const { pending, data: classes } = await useAsyncData('classes', () => $fetch('http://localhost:8080/api/classes/', {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
}));
</script>