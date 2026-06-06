<script lang="ts">
  import {t} from 'i18n'
  import Toasts from 'src/components/Toasts.svelte'
  import {Route, Router} from '@keksworks/svelte-tiny-router'
  import HomePage from 'src/pages/HomePage.svelte'
  import SessionPage from 'src/pages/SessionPage.svelte'
</script>

<svelte:head>
  <title>{t.title}</title>
</svelte:head>

<Toasts/>

<Router>
  <Route path="/" component={HomePage}/>
  <Route path="/session" component={SessionPage}/>
</Router>
