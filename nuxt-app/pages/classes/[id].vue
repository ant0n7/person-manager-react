<template>
  <div class="container">
    <Heading>{{ appClass.classname }}</Heading>
    <!-- <h6 class="subtitle student-email">
      {{ appClass.description }}
    </h6> -->

    <div class="row pt-3" v-if="appClass.subjects.length > 0">
      <Heading tag="h2">Subjects</Heading>
      <div
        class="col-md-3 col-12"
        v-for="subject in appClass.subjects"
        :key="subject.id"  
      >
       <Card
        :title="subject.subjectname"
        :link="`/subjects/${subject.id}`"
        />
      </div>
    </div>
    
    <div class="row pt-3" v-if="appClass.members.length > 0">
      <Heading tag="h2">Students</Heading>
      <div
        class="col-md-3 col-12"
        v-for="student in students"
        :key="student.id"  
      >
       <Card
        :title="student.firstname + ' ' + student.lastname"
        :link="`/students/${student.id}`"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
const route = useRoute()
const uuid = route.params.id;
const uuid_pattern = '/^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i';

const { data: appClass } = await useFetch(`http://localhost:8080/api/classes/${uuid}`, {
  headers: {
    Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
  },
});
</script>

<script>
export default {
  computed: {
    students() {
      return this.appClass.members.filter(member => {
        return member.roles.some(role => role.rolename === 'STUDENT');
      });
    }
  }
}
</script>