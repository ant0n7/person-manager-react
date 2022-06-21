<template>
  <div class="container">
    <!-- <h1>Students <button class="btn btn-primary btn-md mb-2 ms-auto float-end" type="button">Create</button></h1> -->
    <Heading buttonText="Create" buttonLink="/students/create">Students</Heading>

    <div v-if="pending">
      <Alert type="info">
        Loading... {{ pending }}
      </Alert>
    </div>

    <div class="row" v-else>
      <div
        class="col-md-3 col-12 p-2"
        v-for="student in students"
        :key="student.id"
      >
        <Card
          :title="student.firstname + ' ' + student.lastname"
          :subtitle="student.email"
          :link="`/students/${student.id}`"
          />
      </div>
    </div>

    <div class="row" v-if="!students">
      <div class="col-12">
        <Alert type="warning" warning-icon>
          No students found.
        </Alert>
      </div>
    </div>
  </div>
</template>

<script setup>
const { pending, data: students } = await useAsyncData('students', () => $fetch('http://localhost:8080/api/users/', {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
}));
</script>