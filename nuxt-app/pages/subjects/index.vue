<template>
  <div class="container">
    <Heading buttonText="Create" buttonLink="/subjects/create">Subjects</Heading>

    <div class="row">
      <div class="col-md-3 col-12">
        
      </div>
    </div>

    <div v-if="pending">
      <Alert type="info">
        Loading... {{ pending }}
      </Alert>
    </div>

    <div class="row" v-else>
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

    <div class="row" v-if="!subjects">
      <div class="col-12">
        <Alert type="warning" warning-icon>
          No subjects found.
        </Alert>
      </div>
    </div>
  </div>
</template>

<script setup>
const { pending, data: subjects } = await useAsyncData("subjects", () => $fetch("http://localhost:8080/api/subjects/", {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
}));
</script>