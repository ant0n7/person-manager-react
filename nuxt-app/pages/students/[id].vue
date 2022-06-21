<script setup>
const route = useRoute()
const uuid = route.params.id;
const uuid_pattern = '/^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i';

// if (!uuid.match(uuid_pattern)) {
//   return;
// }

const { data: student } = await useFetch(`http://localhost:8080/api/user/${uuid}`, {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
});
</script>

<template>
  <div class="container">
    <Heading>{{ student.username }}</Heading>
    <h6 class="subtitle">{{ student.email }}</h6>

    <div class="row pt-3" v-if="student.subjects">
      <div
        class="col-md-3 col-12"
        v-for="subject in student.subjects"
        :key="subject.id"  
      >
       <Card
        :title="subject.subjectname"
        :link="`/subjects/${subject.id}`"
        />
      </div>
    </div>
  </div>
</template>