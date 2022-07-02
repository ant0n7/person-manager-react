<template>
  <div class="container">
    <!-- TODO(andrin): Implement delete like in single student page. -->
    <Heading>{{ appClass.classname }}</Heading>
    <!-- <h6 class="subtitle student-email">
      {{ appClass.description }}
    </h6> -->

    <div class="row pt-3" v-if="appClass.subjects.length > 0">
      <Heading tag="h2" class="mt-3">Subjects</Heading>
      <div
        class="col-md-3 col-12 p-2"
        v-for="subject in appClass.subjects"
        :key="subject.id"  
      >
       <Card
        :title="subject.subjectname"
        :link="`/subjects/${subject.id}`"
        />
      </div>
    </div>
    
    <div class="row pt-3" v-if="students.length > 0">
      <Heading tag="h2" class="mt-3">Students</Heading>
      <div
        class="col-md-3 col-12 p-2"
        v-for="student in students"
        :key="student.id"  
      >
       <Card v-if="role == 'TEACHER' || username == student.username"
        :title="student.firstname + ' ' + student.lastname"
        :link="`/students/${student.id}`"
        />
        <Card v-else
        :title="student.firstname + ' ' + student.lastname"
        />
      </div>
    </div>
    <div class="row pt-3" v-if="teachers.length > 0">
      <Heading tag="h2" class="mt-3">Teachers</Heading>
      <div
        class="col-md-3 col-12 p-2"
        v-for="teacher in teachers"
        :key="teacher.id"  
      >
       <Card v-if="role == 'TEACHER'"
        :title="teacher.firstname + ' ' + teacher.lastname"
        :link="`/students/${teacher.id}`"
        />
        <Card v-else
        :title="teacher.firstname + ' ' + teacher.lastname"        />
      </div>
    </div>
  </div>
</template>

<script setup>
const route = useRoute();
const uuid = route.params.id;
const uuid_pattern = '/^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i';

const username = useCookie('username').value ?? 'default';
const password = useCookie('password').value ?? 'default';
const role = useCookie('role').value ?? 'default';

const base64auth = btoa(`${username}:${password}`);

const { data: appClass } = await useFetch(`http://localhost:8080/api/classes/${uuid}`, {
  headers: {
    Authorization: `Basic ${base64auth}`,
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
    },
    teachers() {
      return this.appClass.members.filter(member => {
        return member.roles.some(role => role.rolename === 'TEACHER');
      });
    }
  }
}
</script>