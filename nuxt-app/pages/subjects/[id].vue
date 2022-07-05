<script setup>
const route = useRoute()
const uuid = route.params.id;
const uuid_pattern = '/^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i';
const username = useCookie('username').value ?? 'default';
const password = useCookie('password').value ?? 'default';
const role = useCookie('role').value ?? 'default';

const base64auth = btoa(`${username}:${password}`);

const { data: subject } = await useFetch(`http://localhost:8080/api/subjects/${uuid}`, {
  headers: {
    Authorization: `Basic ${base64auth}`,
  },
});
const { data: classes } = await useFetch(`http://localhost:8080/api/classes/subject/${uuid}`, {
  headers: {
    Authorization: `Basic ${base64auth}`,
  },
});
</script>

<template>
  <div class="container">
    <Heading>{{ subject.subjectname }}</Heading>

    <div class="row pt-3" v-if="role != 'STUDENT' && classes.length > 0">
      <Heading class="mt-3" tag="h3">Classes</Heading>
      <div
        class="col-lg-3 col-md-4 col-sm-6 col-12 p-2"
        v-for="appclass in classes"
        :key="appclass.id"  
      >
       <Card
        :title="appclass.classname"
        :link="`/classes/${appclass.id}`"
        />
      </div>
    </div>
  </div>
</template>