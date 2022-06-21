<template>
  <div class="container">
    <!-- <h1>Students <button class="btn btn-primary btn-md mb-2 ms-auto float-end" type="button">Create</button></h1> -->
    <Heading buttonText="Create" buttonLink="/students/create">Students</Heading>

    <div v-if="pending">
      <Alert type="info">
        Loading...
      </Alert>
    </div>

    <div class="row" v-else>
      <div
        class="col-md-3 col-12 p-2"
        v-for="student in students"
        :key="student.id"
      >
        <Card
          :title="student.username"
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
import Card1 from '~~/components/Card.vue';
// const { data: students } = await useFetch(() => 'http://localhost:8080/api/user', {
//   // baseURL: config.API_BASE_URL,
//   // baseURL: 'http://localhost:8080',
//   headers: {
//     // 'Content-Type': 'application/json',
//     // 'Accept': 'application/json',
//     // 'Authorization': `Basic ${Buffer.from('andrin' + ":" + 'klarer', 'ascii').toString('base64')}`
//     'Authorization': 'YW5kcmluOmtsYXJlcg==',
//   },
// })

// Fetch data from external API
const { pending, data: students } = await useLazyFetch("http://localhost:8080/api/users/", {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
});
watch(students, (newStudents) => {
  console.log("Students Updated");
  console.log(JSON.stringify(newStudents));
})
</script>