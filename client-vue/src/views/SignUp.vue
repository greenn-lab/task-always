<template>
  <v-container>
    <v-row>
      <v-card width="800">
        <v-col md>
          <v-card-title>Task Always</v-card-title>
          <v-card-subtitle>Let's join to of us</v-card-subtitle>
          <v-form
            v-model="valid"
            ref="form"
            @submit.prevent="ok"
            autocomplete="off"
          >
            <v-card-text>

              <v-text-field
                v-model="fields.nickname"
                label="nickname"
                required
                validate-on-blur
                :counter="10"
                :rules="[v => v.length < 2 ? 'too short' : true]"
              ></v-text-field>

              <v-text-field
                v-model="fields.email"
                label="e-mail"
                required
                validate-on-blur
                :rules="[v => /\w+@\w+\..+/.test(v) ? true : 'incorrect']"
              />

              <v-text-field
                v-model="fields.pin"
                label="password"
                hint="Make it difficult"
                required
                :type="!showPassword ? 'password' : 'text'"
                :append-icon="!showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append="showPassword = !showPassword"
              />

              <v-switch
                label="Do you agree?"
                required
                :rules="[v => !!v || 'You must agree to continue!']"
              ></v-switch>
            </v-card-text>

            <v-card-actions>
              <v-btn
                type="submit"
                color="success"
                large
                :disabled="!valid"
              >
                Sign up
              </v-btn>
            </v-card-actions>
          </v-form>
        </v-col>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
  import axios from 'axios'

  export default {
    methods: {
      ok() {
        axios.post('/user/sign-up', this.fields)
          .then(res => console.log(res))
          .catch(err => console.log(err))

        console.log('... is ok!')
      }
    },
    data() {
      return {
        valid: false,
        showPassword: false,
        fields: {
          nickname: '',
          email: '',
          pin: ''
        }
      }
    }
  }
</script>
