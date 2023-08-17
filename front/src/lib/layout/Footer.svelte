<script>
  import {page} from "$app/stores";

  let showFooter = true;
  // @ts-ignore
  $: {
    showFooter = $page.url.pathname !== '/login'  &&  $page.url.pathname !== '/signup' && $page.url.pathname !== '/verification';
  }
</script>

{#if showFooter}
<footer aria-label="Site Footer" class="w-full border-t border-gray-500/30 dark:border-gray-500/70 mt-10">
  <div class="mx-auto max-w-6xl pb-12 pt-10">
    <div class="flex justify-center">
      <img class="h-10 w-auto" src="/logo.png">
      <p class="ml-2 mt-2 text-lg text-gray-500">GROUND</p>
    </div>

    <ul class="mt-12 flex flex-wrap justify-center space-x-16 gap-6 md:gap-8 lg:gap-12">
      <li>
        <a class="text-gray-700 transition hover:text-gray-700/75" href="/">
          Customer Center
        </a>
      </li>

      <li>
        <a class="text-gray-700 transition hover:text-gray-700/75" href="/">
          Notify
        </a>
      </li>

      <li>
        <a class="text-gray-700 transition hover:text-gray-700/75" href="/">
          Help
        </a>
      </li>

      <li>
        <a class="text-gray-700 transition hover:text-gray-700/75" href="/">
          Contact
        </a>
      </li>

      <li>
        <a class="text-gray-700 transition hover:text-gray-700/75" href="/">
          Report
        </a>
      </li>

      <li>
        <a class="text-gray-700 transition hover:text-gray-700/75" href="/">
          Terms of Use
        </a>
      </li>
    </ul>
  </div>
</footer>
{/if}
