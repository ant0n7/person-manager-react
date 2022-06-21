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
const { data: subjects } = await useFetch("http://localhost:8080/api/subject/", {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
});
</script>

<template>
  <div class="container">
    <!-- <h1>Students <button class="btn btn-primary btn-md mb-2 ms-auto float-end" type="button">Create</button></h1> -->
    <Heading buttonText="Create" buttonLink="/subjects/create">Subjects</Heading>

    <div class="row">
      <div class="col-md-3 col-12">
        
      </div>
    </div>

    <div class="row" v-if="subjects">
      <div
        class="col-md-3 col-12 p-2"
        v-for="subject in subjects"
        :key="subject.id"
      >
        <Card
          :title="subject.subjectname"
          :link="`/subjects/${subject.id}`"
          />
      </div>
    </div>

    <div class="row" v-else>
      <div class="col-12">
        <Alert type="warning">
          No subjects found.
        </Alert>
      </div>
    </div>
  </div>
</template>
