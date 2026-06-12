<script lang="ts">
  import {onMount} from 'svelte'
  import api from 'src/api/api'
  import type {User} from 'src/api/types'

  let user: User

  onMount(async () => {
    user = await api.get('users/user')
  })
</script>

{JSON.stringify(user)}
