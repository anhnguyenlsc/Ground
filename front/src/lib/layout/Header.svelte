<script>
	import { page } from '$app/stores';
	import { browser } from '$app/environment';
	import {writable} from 'svelte/store';
	import { authentication } from "$lib/current";

	export const darkmode = writable(false);

	let isDarkmode = false;

	if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
		isDarkmode = localStorage.getItem('darkmode') === 'true';
		if (isDarkmode === true) {
			window.document.body.classList.toggle('dark')
		}
 	}

	function handleClick() {
		isDarkmode = !isDarkmode;
		// @ts-ignore
    // eslint-disable-next-line no-unused-vars
		darkmode.subscribe((val) => browser && localStorage.setItem('darkmode', isDarkmode));
		window.document.body.classList.toggle('dark')
}
	let showHeader = true;
		// @ts-ignore
		$: {
		showHeader = $page.url.pathname !== '/login'  &&  $page.url.pathname !== '/signup';
	}

	//TODO: Handle signOut
	// const singOut = () => authentication.signOut();

	//Log avatar
	// console.log('Avatar: ' + $authentication?.principal.avatar);


</script>

{#if showHeader}
<header class="sticky top-0 z-20 flex h-26 items-center border-b border-b-gray-500/30 bg-white text-sm font-medium leading-6 dark:border-b-gray-500/70 dark:bg-gray-800">
	<div class="w-full container mx-auto max-w-6xl flex flex-wrap items-center mt-0 pt-3 md:pb-0">
		<!-- Header top -->
		<div class="mx-auto flex w-full px-4 lg:px-0 border-b pb-3 ">
			<div class="flex w-full items-center justify-between">
				<div class="flex">
					<!-- Logo -->
					<a class="flex items-center" href="/#/">
						<span class="sr-only">GROUND LOGO</span>
						<img class="h-10 w-auto" src="/logo.png" alt="GROUND Logo"/>
					</a>
					<div class="ml-4 hidden items-center space-x-16 md:flex lg:ml-[105px]">
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium hover:no-underline" href="">HITS</a>
						</div>
						<div class="h-3 w-[1px] bg-gray-400 dark:bg-gray-400/70"></div>
						<div class="shrink-0">
							<a class="text-violet-500 hover:text-violet-700 dark:text-violet-500 dark:hover:text-violet-700 relative text-sm font-medium" href="">
								<span class="hover:no-underline">TREND</span>
							</a>
						</div>
						<div class="h-3 w-[1px] bg-gray-400 dark:bg-gray-400/70"></div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">CHEMISTRY</span>
							</a>
						</div>
						<!-- Search Bar -->
						<div class="shrink-0">
							<div class="w-40 lg:w-[235px]">
								<div class="flex h-[35px] items-center rounded-[43px] border border-gray-300 py-0 transition-colors focus-within:border-blue-500 focus-within:ring-1 focus-within:ring-blue-500 dark:border-gray-700 dark:bg-gray-700">
									<button class="-my-0.5 -mr-1.5 flex h-8 w-8 flex-shrink-0 items-center justify-center transition duration-300">
										<span class="sr-only">Search Bar</span>
										<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-3.5 w-3.5 text-gray-500 hover:text-blue-500 dark:text-gray-400 dark:hover:text-blue-200">
											<path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"></path>
										</svg>
									</button>
									<input class="w-full border-none bg-transparent p-0 pr-2 text-sm font-normal placeholder-gray-500/80 focus:text-gray-800 dark:focus:text-gray-100 focus:color-gray-100 focus:border-gray-500 focus:outline-none focus:ring-0" type="text" placeholder="Search">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="hidden items-center md:flex">
					<div class="flex items-center gap-x-6">
						<button on:click={handleClick} class="bg-blue-500 dark:bg-gray-700 relative inline-flex h-8 w-14 items-center rounded-full sm:h-6 sm:w-11" id="headlessui-switch-:r2:" role="switch" tabindex="0" aria-checked="false" type="button">
							<span class="sr-only">Enable Darkmode</span>
							<span class={`${isDarkmode ? "absolute left-0.5 top-1.5 sm:top-" : "absolute right-0.5 top-1 sm:top-0.5"}`}>
								<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true" class="{`${isDarkmode ? "h-5 w-5 text-gray-400 sm:h-4 sm:w-4" : "h-5 w-5 text-yellow-400 sm:h-5 sm:w-5"}`}" >
									<path d={`${isDarkmode ? "M9.528 1.718a.75.75 0 01.162.819A8.97 8.97 0 009 6a9 9 0 009 9 8.97 8.97 0 003.463-.69.75.75 0 01.981.98 10.503 10.503 0 01-9.694 6.46c-5.799 0-10.5-4.701-10.5-10.5 0-4.368 2.667-8.112 6.46-9.694a.75.75 0 01.818.162z" : "M12 2.25a.75.75 0 01.75.75v2.25a.75.75 0 01-1.5 0V3a.75.75 0 01.75-.75zM7.5 12a4.5 4.5 0 119 0 4.5 4.5 0 01-9 0zM18.894 6.166a.75.75 0 00-1.06-1.06l-1.591 1.59a.75.75 0 101.06 1.061l1.591-1.59zM21.75 12a.75.75 0 01-.75.75h-2.25a.75.75 0 010-1.5H21a.75.75 0 01.75.75zM17.834 18.894a.75.75 0 001.06-1.06l-1.59-1.591a.75.75 0 10-1.061 1.06l1.59 1.591zM12 18a.75.75 0 01.75.75V21a.75.75 0 01-1.5 0v-2.25A.75.75 0 0112 18zM7.758 17.303a.75.75 0 00-1.061-1.06l-1.591 1.59a.75.75 0 001.06 1.061l1.591-1.59zM6 12a.75.75 0 01-.75.75H3a.75.75 0 010-1.5h2.25A.75.75 0 016 12zM6.697 7.757a.75.75 0 001.06-1.06l-1.59-1.591a.75.75 0 00-1.061 1.06l1.59 1.591z"}`}></path>
								</svg>
							</span>
							<span class={`${isDarkmode ? "translate-x-7 sm:translate-x-[22px]" : "translate-x-1 sm:translate-x-0.5" } inline-block h-6 w-6 transform rounded-full bg-white transition sm:h-5 sm:w-5`} ></span>
						</button>
					</div>
				</div>

				{#if $authentication}
					<div class="ml-[102px] mr-5">
						<a href="">
							<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-5 w-5 text-gray-400 hover:cursor-pointer hover:text-blue-500 dark:hover:text-blue-200">
							<path stroke-linecap="round" stroke-linejoin="round" d="M17.593 3.322c1.1.128 1.907 1.077 1.907 2.185V21L12 17.25 4.5 21V5.507c0-1.108.806-2.057 1.907-2.185a48.507 48.507 0 0111.186 0z"></path>
							</svg>
						</a>
					</div>
					<div class="mr-5">
						<div class="relative">
							<button class="relative -my-1 flex h-8 w-6 items-center justify-center rounded-sm text-gray-400 hover:text-blue-500 focus:outline-0 focus:ring-0 dark:hover:text-blue-200" type="button">
								<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-6 w-6">
									<path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0"></path>
								</svg>
							</button>
						</div>
					</div>
					<div class="relative">
						<div class="relative">
							<button class="flex rounded-full bg-white focus:outline-none dark:bg-gray-800" type="button">
							<span class="sr-only">Open user menu</span>
							<span class="hidden flex-shrink-0 sm:flex">
								<img class="h-8 w-8 rounded-full" src={$authentication.principal?.avatar} alt="">
							</span>
							</button>
						</div>
					</div>
				{:else}
					<!-- USER NOT LOGIN (ANONYMOUS)-->
					<a class="ml-2 hidden h-[35px] w-[85px] items-center justify-center rounded-3xl border border-gray-500/30 bg-white text-center text-xs font-medium text-gray-900 shadow-sm hover:bg-gray-100 focus:outline-none dark:border-gray-500/70 dark:bg-gray-700 dark:text-gray-100 dark:hover:bg-gray-600 sm:flex lg:ml-10" href="/login">
						SIGN IN</a>
					<button class="ml-2.5 hidden h-[35px] w-[85px] items-center justify-center rounded-3xl border border-transparent bg-blue-500 px-2 py-0.5 text-xs font-medium text-white hover:bg-blue-400 focus:outline-none focus:ring-offset-0 sm:flex">
						<a href="/signup">SIGN UP</a>
					</button>
				{/if}	

				<div class="right-0 flex shrink-0 md:hidden">
					<button class="inline-flex items-center justify-center rounded-md bg-transparent p-2 text-blue-500 last:-mr-2 hover:bg-gray-900/10 focus:outline-none dark:hover:bg-gray-100/10" id="headlessui-popover-button-:r0:" type="button" aria-expanded="false" data-headlessui-state="">
						<span class="sr-only">Open main menu</span>
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="block h-6 w-6">
							<path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"></path>
						</svg>
					</button>
				</div>
			</div>
		</div>
		<!-- Header bottom -->
		<nav class="mx-auto flex w-full px-4 pt-3 pb-3 lg:px-0">
			<div class="flex w-full items-center justify-between">
				<div class="flex">
					<div class="hidden items-center space-x-24 md:flex">
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium hover:no-underline" href="">
								<span class="hover:no-underline">REALTIME</span>
							</a>
						</div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">SPORTS</span>
							</a>
						</div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">GAMING</span>
							</a>
						</div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">BUSINESS</span>
							</a>
						</div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">CRYPTO</span>
							</a>
						</div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">MOVIE</span>
							</a>
						</div>
						<div class="shrink-0">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="">
								<span class="hover:no-underline">CELEBRITY</span>
							</a>
						</div>
						<div class="shrink-0">
							<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 ml-16 dark:fill-gray-100" viewBox="0 0 28 28" fill="#000000" >
								<path clip-rule="evenodd" d="M5.7 12.1143C4.82423 12.1143 4.11429 12.8242 4.11429 13.7C4.11429 14.5758 4.82423 15.2857 5.7 15.2857C6.57577 15.2857 7.28571 14.5758 7.28571 13.7C7.28571 12.8242 6.57577 12.1143 5.7 12.1143ZM2 13.7C2 11.6565 3.65655 10 5.7 10C7.74345 10 9.4 11.6565 9.4 13.7C9.4 15.7435 7.74345 17.4 5.7 17.4C3.65655 17.4 2 15.7435 2 13.7Z" fill-rule="evenodd"/>
								<path clip-rule="evenodd" d="M22.7 12.1143C21.8242 12.1143 21.1143 12.8242 21.1143 13.7C21.1143 14.5758 21.8242 15.2857 22.7 15.2857C23.5758 15.2857 24.2857 14.5758 24.2857 13.7C24.2857 12.8242 23.5758 12.1143 22.7 12.1143ZM19 13.7C19 11.6565 20.6565 10 22.7 10C24.7435 10 26.4 11.6565 26.4 13.7C26.4 15.7435 24.7435 17.4 22.7 17.4C20.6565 17.4 19 15.7435 19 13.7Z"  fill-rule="evenodd"/>
								<path clip-rule="evenodd" d="M14.2 12.1143C13.3242 12.1143 12.6143 12.8242 12.6143 13.7C12.6143 14.5758 13.3242 15.2857 14.2 15.2857C15.0758 15.2857 15.7857 14.5758 15.7857 13.7C15.7857 12.8242 15.0758 12.1143 14.2 12.1143ZM10.5 13.7C10.5 11.6565 12.1565 10 14.2 10C16.2435 10 17.9 11.6565 17.9 13.7C17.9 15.7435 16.2435 17.4 14.2 17.4C12.1565 17.4 10.5 15.7435 10.5 13.7Z"  fill-rule="evenodd"/>
							</svg>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</div>
</header>
{/if}
