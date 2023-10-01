<script>
	import { page } from '$app/stores';
	import { browser } from '$app/environment';
	import { writable } from 'svelte/store';
	import { fade, slide } from 'svelte/transition'
	import { authentication } from "$lib/current";
	import { Storage } from '$lib/support';
	import axios from 'axios';
	import { onMount } from 'svelte';

	//Get category data from API
	/**
 * @type {any[]}
 */
	let data = [];
	let dataType = [];
	let currentCategory = '';
	let currentType = '';
	let currentCatid = '';
	let currentCircle = '';
	let currentCircleid = '';

	let dropDownItem = [];
	let dropdownCategories = [];

	let dropDownTypes = [];

	let dropDownTypeAll = [];
	let dropDownTypeItem = [];

	let types = [];

	let menuExtendVisible = false;

	let showAll = false;
	let showAllType = false;

    const toggleShowAll = () => { showAll = !showAll; }
	const toggleShowAllType = () => { showAllType = !showAllType; }
    const toggleMenuExtend = () => { menuExtendVisible = !menuExtendVisible; }

	let getLocation = Storage.get('currentLocation');
	if (getLocation)
	{
		[currentCatid, currentCategory, currentType, currentCircle, currentCircleid] = Object.values(getLocation);
	}

	onMount(async () => {
		const catApi = 'http://localhost:8080/api/authentication/category';
		const response = await axios.get(catApi);
		
		const category = response.data;
		data = category.map((cat) => ({id: cat.id ,title: cat.title, icon: cat.icon}));

		let promises = data.map(async (cat) => {
			const catTypeApi = 'http://localhost:8080/api/authentication/categoryType/' + cat.id;
			const response = await axios.get(catTypeApi);
			return response.data.map((tp) => ({id: tp.id, title: tp.title, categoryId: tp.categoryId}));
		});

		//Flatten the types array to single array
		types = (await Promise.all(promises)).flat();

		// Filter out the current category from the list of categories		
		dropDownTypes = types.filter((item) => item.title !== currentType && item.categoryId == currentCatid);

		dropdownCategories = data.filter((cat) => cat.title !== currentCategory)
		dropDownItem = data.filter((cat) => !data.slice(0,7).includes(cat));

		if (currentCatid != '') {
				const catTypeApi = 'http://localhost:8080/api/authentication/categoryType/' + currentCatid;
				const response = await axios({
				method: "GET",
				url: catTypeApi,
			})

			const categoryType = response.data;
			dataType = categoryType.map((ctp) => ({id: ctp.id, title: ctp.title}));
			dropDownTypeAll = dataType.filter((ctp) => ctp.title !== currentCircle);
		}
	})

	function selectCategory(catId, category, curType, cCir, cCirId) {
		[currentCatid, currentCategory, currentType] = [catId, category, curType];
		if (cCir !== undefined && cCirId !== undefined) {
			[currentCircle, currentCircleid] = [cCir, cCirId];
		}

		else {
			[currentCircle, currentCircleid] = ['', ''];
		}
 		
		let currentLocation = {currentCatid, currentCategory, currentType, currentCircle, currentCircleid};
		Storage.set('currentLocation', currentLocation);
	}

	function setLocation(location) {
		Storage.set('currentLocation', location);
	}

	export const darkmode = writable(false);

	let isDarkmode = false;
	let avatarPic = '';

	if (typeof window !== 'undefined' && typeof localStorage !== 'undefined' || typeof sessionStorage !== 'undefined') {
		isDarkmode = localStorage.getItem('darkmode') === 'true';
		if (isDarkmode === true) {
			window.document.body.classList.toggle('dark')
		}
		avatarPic = sessionStorage.getItem('avatar');

		// console.log(localStorage.getItem('currentLocation'));
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
	$: { showHeader = $page.url.pathname !== '/login'  &&  $page.url.pathname !== '/signup' && $page.url.pathname !== '/verification'; }

	function signOut() {
		authentication.signOut();
		location.reload();
	}

	//Log avatar
	// console.log('Avatar: ' + $authentication?.principal.avatar);
	// console.log(avatarPic);

	function resetCurrentLocation() {
		Storage.reset('currentLocation');
	}

	function resetCurrentCat() {
		let currentLocation = JSON.parse(localStorage.getItem('currentLocation'));
		currentLocation.value.currentCircle = '';
		currentLocation.value.currentCircleid = '';
		// console.log(type);
		localStorage.setItem('currentLocation', JSON.stringify(currentLocation));
	}

</script>

{#if showHeader}
<header class="sticky top-0 flex h-26 items-center border-b border-b-gray-500/30 bg-white text-sm font-medium leading-6 dark:border-b-gray-500/70 dark:bg-gray-800 z-20" data-sveltekit-reload>
	<div class="w-full container mx-auto max-w-6xl flex flex-wrap items-center mt-0 pt-3 md:pb-0">
		<!-- Header top -->
		<div class="mx-auto flex w-full px-4 lg:px-0 border-b pb-3 ">
			<div class="flex w-full items-center justify-between">
				<div class="flex">
					<!-- Logo -->
					<!-- svelte-ignore a11y-no-static-element-interactions -->
					<a class="flex items-center" href="/#/">
						<span class="sr-only">GROUND LOGO</span>
						<!-- svelte-ignore missing-declaration -->
						<!-- svelte-ignore a11y-click-events-have-key-events -->
						<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
						<img on:click={resetCurrentLocation} class="h-10 w-auto" src="/logo.png" alt="GROUND Logo"/>
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

				{#if $authentication || avatarPic}
					<div class="ml-[102px] mr-5">
						<a href="/savedPost" on:click={() => setLocation('Saved Post')}>
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
							<button class="peer flex rounded-full bg-white focus:outline-none dark:bg-gray-800" type="button">
							<span class="sr-only">Open user menu</span>
							<span class="hidden flex-shrink-0 sm:flex">
								<img class="h-8 w-8 rounded-full" src={avatarPic ?? $authentication.principal?.avatar} alt="avatar">
							</span>
							</button>

							<!--user menu-->
							<div class="absolute right-0 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5 z-40 hidden peer-hover:block hover:block">
								<a href="/account" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
									Account Settings
								</a>
								<a href="" on:click={signOut} class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
									Log out
								</a>
							</div>
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
			<div class="flex w-full items-center justify-between z-20">
				<div class="flex">
					{#if $page.url.pathname === '/'}
					<!-- Category render -->
					<div class="hidden items-center space-x-24 md:flex z-20">
						{#each data.slice(0, 7) as item}
							<div class="shrink-0 z-20 group relative">
								<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium hover:no-underline" href="">
									<span class="hover:no-underline uppercase">{item.title}</span>
								</a>
								<div class="group-hover:block dropdown-menu absolute hidden h-auto">
									<ul class="top-0 w-40 bg-white shadow px-6 py-4">
										{#each types as type}
											{#if item.id == type.categoryId}
												<!-- svelte-ignore a11y-click-events-have-key-events -->
												<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
												<li class="py-1" on:click={() => selectCategory(type.categoryId, item.title, type.title)}>
													<a class="block uppercase text-gray-900 hover:text-blue-500 dark:text-black dark:hover:text-blue-200 text-sm font-medium hover:no-underline cursor-pointer" href="/{type.id}">{type.title}</a>
												</li>
											{/if}
										{/each}
									</ul>
								</div>
							</div>
						{/each}
						<!-- svelte-ignore a11y-click-events-have-key-events -->
						<!-- svelte-ignore a11y-no-static-element-interactions -->
						<div class="shrink-0 group">
							{#if menuExtendVisible}
								<svg on:click={toggleMenuExtend} in:slide={{ duration: 250 }} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="h-6 w-6 ml-16 hover:fill-blue-500 cursor-pointer dark:fill-gray-100">
									<path fill-rule="evenodd" d="M5.47 5.47a.75.75 0 011.06 0L12 10.94l5.47-5.47a.75.75 0 111.06 1.06L13.06 12l5.47 5.47a.75.75 0 11-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 01-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 010-1.06z" clip-rule="evenodd" />
							  	</svg>
							  
							{:else}
								<svg in:slide={{duration: 250}}  on:click={toggleMenuExtend} xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 ml-16 hover:fill-blue-500 cursor-pointer dark:fill-gray-100" viewBox="0 0 28 28" fill="#000000">
									<path clip-rule="evenodd" d="M5.7 12.1143C4.82423 12.1143 4.11429 12.8242 4.11429 13.7C4.11429 14.5758 4.82423 15.2857 5.7 15.2857C6.57577 15.2857 7.28571 14.5758 7.28571 13.7C7.28571 12.8242 6.57577 12.1143 5.7 12.1143ZM2 13.7C2 11.6565 3.65655 10 5.7 10C7.74345 10 9.4 11.6565 9.4 13.7C9.4 15.7435 7.74345 17.4 5.7 17.4C3.65655 17.4 2 15.7435 2 13.7Z" fill-rule="evenodd"/>
									<path clip-rule="evenodd" d="M22.7 12.1143C21.8242 12.1143 21.1143 12.8242 21.1143 13.7C21.1143 14.5758 21.8242 15.2857 22.7 15.2857C23.5758 15.2857 24.2857 14.5758 24.2857 13.7C24.2857 12.8242 23.5758 12.1143 22.7 12.1143ZM19 13.7C19 11.6565 20.6565 10 22.7 10C24.7435 10 26.4 11.6565 26.4 13.7C26.4 15.7435 24.7435 17.4 22.7 17.4C20.6565 17.4 19 15.7435 19 13.7Z"  fill-rule="evenodd"/>
									<path clip-rule="evenodd" d="M14.2 12.1143C13.3242 12.1143 12.6143 12.8242 12.6143 13.7C12.6143 14.5758 13.3242 15.2857 14.2 15.2857C15.0758 15.2857 15.7857 14.5758 15.7857 13.7C15.7857 12.8242 15.0758 12.1143 14.2 12.1143ZM10.5 13.7C10.5 11.6565 12.1565 10 14.2 10C16.2435 10 17.9 11.6565 17.9 13.7C17.9 15.7435 16.2435 17.4 14.2 17.4C12.1565 17.4 10.5 15.7435 10.5 13.7Z"  fill-rule="evenodd"/>
								</svg>
							{/if}
						</div>
					</div>
					<!-- Breadcrumb check appear -->
					{:else if currentCategory !== ''}
						<nav>
							<div class="wrapper w-full grid grid gap-8">
								<ol class="breadcrumbs breadcrumbs--one flex list-none m-0 pl-4 text-sm bg-white overflow-hidden relative outline-none">
									<li class="relative text-white outline-none" itemprop="itemListElement">
										<a class="breadcrumb outline-none" href="http://localhost:5173/#/" itemprop="item" on:click={resetCurrentLocation}>
										<i itemprop="name">
											<svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
												<path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path>
											</svg>
											<i class="px-2 font-extrabold">GROUND</i>
										</i>
										</a>
										<meta itemprop="position" content="1" />
									</li>
									<!-- svelte-ignore a11y-click-events-have-key-events -->
									<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
									<li on:click={resetCurrentCat} class="text-white outline-none group relative" itemprop="itemListElement">
										<a class="breadcrumb outline-none" href="http://localhost:5173/{currentCategory}" itemprop="item">
											<span class="relative" itemprop="name">
												<span class="capitalize">{currentCategory}</span>
											</span>
										</a>
										<meta itemprop="position" content="2" />
										<ul on:mouseleave={() => { showAll = false }} class="fixed hidden top-30 w-52 block group-hover:block group-focus:block bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5">
											{#each (showAll ? dropdownCategories : dropdownCategories.slice(0,5)) as dropDownCat}
												<!-- svelte-ignore a11y-click-events-have-key-events -->
												<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
												<li on:click={resetCurrentCat} class="capitalize text-gray-900 p-3 cursor-pointer dark:text-gray-100 dark:text-blue-100 text-base font-medium hover:bg-gradient-to-r hover:from-blue-200 hover:to-blue-0 transition duration-300 ease-in-out hover:border-l-4 rounded-l-sm hover:border-blue-500 ">
													<div class="flex items-center">
														<div class="w-6 h-6 mr-2 rounded overflow-hidden">
															<img src="{dropDownCat.icon}" alt="pic" />
														</div>
														<a href="/{dropDownCat.title}">
															<!-- svelte-ignore a11y-no-static-element-interactions -->
															<!-- svelte-ignore a11y-click-events-have-key-events -->
															<span on:click={() => selectCategory(currentCatid, dropDownCat.title)}>{dropDownCat.title}</span>
														</a>
													</div>
												</li>
											{/each}

											<!-- svelte-ignore missing-declaration -->
											<!-- svelte-ignore a11y-click-events-have-key-events -->
											<!-- svelte-ignore a11y-no-static-element-interactions -->
											{#if !showAll}
												<div class="flex justify-center items-center animate-bounce">
													<svg on:click={toggleShowAll} xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 hover:fill-blue-500 cursor-pointer dark:fill-gray-100 items-center" viewBox="0 0 28 28" fill="#000000">
														<path clip-rule="evenodd" d="M5.7 12.1143C4.82423 12.1143 4.11429 12.8242 4.11429 13.7C4.11429 14.5758 4.82423 15.2857 5.7 15.2857C6.57577 15.2857 7.28571 14.5758 7.28571 13.7C7.28571 12.8242 6.57577 12.1143 5.7 12.1143ZM2 13.7C2 11.6565 3.65655 10 5.7 10C7.74345 10 9.4 11.6565 9.4 13.7C9.4 15.7435 7.74345 17.4 5.7 17.4C3.65655 17.4 2 15.7435 2 13.7Z" fill-rule="evenodd"/>
														<path clip-rule="evenodd" d="M22.7 12.1143C21.8242 12.1143 21.1143 12.8242 21.1143 13.7C21.1143 14.5758 21.8242 15.2857 22.7 15.2857C23.5758 15.2857 24.2857 14.5758 24.2857 13.7C24.2857 12.8242 23.5758 12.1143 22.7 12.1143ZM19 13.7C19 11.6565 20.6565 10 22.7 10C24.7435 10 26.4 11.6565 26.4 13.7C26.4 15.7435 24.7435 17.4 22.7 17.4C20.6565 17.4 19 15.7435 19 13.7Z"  fill-rule="evenodd"/>
														<path clip-rule="evenodd" d="M14.2 12.1143C13.3242 12.1143 12.6143 12.8242 12.6143 13.7C12.6143 14.5758 13.3242 15.2857 14.2 15.2857C15.0758 15.2857 15.7857 14.5758 15.7857 13.7C15.7857 12.8242 15.0758 12.1143 14.2 12.1143ZM10.5 13.7C10.5 11.6565 12.1565 10 14.2 10C16.2435 10 17.9 11.6565 17.9 13.7C17.9 15.7435 16.2435 17.4 14.2 17.4C12.1565 17.4 10.5 15.7435 10.5 13.7Z"  fill-rule="evenodd"/>
													</svg>
												</div>
											{/if}
										</ul>
									</li>
												
									{#if currentType !== ''}
										<li class="relative text-white outline-none group relative" itemprop="itemListElement">
											<a class="breadcrumb outline-none" href="/{currentType}" itemprop="item">
											<span itemprop="name">
												<span class="capitalize">{currentType}</span>
											</span>
											</a>
											<meta itemprop="position" content="3" />

											<ul on:mouseleave={() => { showAll = false }} class="fixed hidden top-30 w-52 block group-hover:block group-focus:block bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5">
												{#each (showAll ? dropDownTypes : dropDownTypes.slice(0,5)) as dropType}
													<!-- svelte-ignore a11y-click-events-have-key-events -->
													<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
													<li on:click={resetCurrentCat} class="capitalize text-gray-900 p-3 cursor-pointer dark:text-gray-100 dark:text-blue-100 text-base font-medium hover:bg-gradient-to-r hover:from-blue-200 hover:to-blue-0 transition duration-300 ease-in-out hover:border-l-4 rounded-l-sm hover:border-blue-500 ">
														<div class="flex items-center">
															<a href="/{dropType.id}">
																<!-- svelte-ignore a11y-no-static-element-interactions -->
																<!-- svelte-ignore a11y-click-events-have-key-events -->
																<span on:click={() => selectCategory(dropType.categoryId, dropType.title)}>{dropType.title}</span>
															</a>
														</div>
													</li>
												{/each}
	
												<!-- svelte-ignore missing-declaration -->
												<!-- svelte-ignore a11y-click-events-have-key-events -->
												<!-- svelte-ignore a11y-no-static-element-interactions -->
												{#if !showAll}
													<div class="flex justify-center items-center animate-bounce">
														<svg on:click={toggleShowAll} xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 hover:fill-blue-500 cursor-pointer dark:fill-gray-100 items-center" viewBox="0 0 28 28" fill="#000000">
															<path clip-rule="evenodd" d="M5.7 12.1143C4.82423 12.1143 4.11429 12.8242 4.11429 13.7C4.11429 14.5758 4.82423 15.2857 5.7 15.2857C6.57577 15.2857 7.28571 14.5758 7.28571 13.7C7.28571 12.8242 6.57577 12.1143 5.7 12.1143ZM2 13.7C2 11.6565 3.65655 10 5.7 10C7.74345 10 9.4 11.6565 9.4 13.7C9.4 15.7435 7.74345 17.4 5.7 17.4C3.65655 17.4 2 15.7435 2 13.7Z" fill-rule="evenodd"/>
															<path clip-rule="evenodd" d="M22.7 12.1143C21.8242 12.1143 21.1143 12.8242 21.1143 13.7C21.1143 14.5758 21.8242 15.2857 22.7 15.2857C23.5758 15.2857 24.2857 14.5758 24.2857 13.7C24.2857 12.8242 23.5758 12.1143 22.7 12.1143ZM19 13.7C19 11.6565 20.6565 10 22.7 10C24.7435 10 26.4 11.6565 26.4 13.7C26.4 15.7435 24.7435 17.4 22.7 17.4C20.6565 17.4 19 15.7435 19 13.7Z"  fill-rule="evenodd"/>
															<path clip-rule="evenodd" d="M14.2 12.1143C13.3242 12.1143 12.6143 12.8242 12.6143 13.7C12.6143 14.5758 13.3242 15.2857 14.2 15.2857C15.0758 15.2857 15.7857 14.5758 15.7857 13.7C15.7857 12.8242 15.0758 12.1143 14.2 12.1143ZM10.5 13.7C10.5 11.6565 12.1565 10 14.2 10C16.2435 10 17.9 11.6565 17.9 13.7C17.9 15.7435 16.2435 17.4 14.2 17.4C12.1565 17.4 10.5 15.7435 10.5 13.7Z"  fill-rule="evenodd"/>
														</svg>
													</div>
												{/if}
											</ul>
										</li>
									{/if}

									{#if currentCircle !== ''}
										<li class="relative text-white outline-none group relative" itemprop="itemListElement">
											<a class="breadcrumb outline-none" href="/category/{currentCatid}/{currentCircleid}" itemprop="item">
											<span itemprop="name">
												<span class="capitalize">{currentCircle}</span>
											</span>
											</a>
											<meta itemprop="position" content="4" />
										</li>
									{/if}
								</ol>
							</div>
						</nav>							
					{/if}
				</div>
			</div>

			{#if menuExtendVisible} 
			<div in:fade={{duration: 100}} out:fade={{duration: 100}} class="menu-extend absolute top-16 max-w-6xl w-full items-center rounded-md shadow-lg drop-shadow-lg bg-white ring-1 ring-black ring-opacity-5 z-10">
				<div class="border-t-2 rounded p-6"></div>
				<div class="bg-gray-200 border-t p-2 list-none flex items-center space-x-24 justify-center">
					{#each dropDownItem as dropItem}
						<li class="uppercase group">
							<a class="text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 text-sm font-medium" href="/category/{dropItem.id}">
								<!-- svelte-ignore a11y-no-static-element-interactions -->
								<!-- svelte-ignore a11y-click-events-have-key-events -->
								<span class="hover:no-underline uppercase" on:click={() => selectCategory(dropItem.title)}>{dropItem.title}</span>
							</a>

							<div class="group-hover:block dropdown-menu absolute hidden h-auto z-20">
								<ul class="top-0 w-full bg-white shadow px-6 py-4">
									{#each types as type}
										{#if dropItem.id == type.categoryId}
											<li class="py-1">
												<a class="block uppercase text-gray-900 hover:text-blue-500 dark:text-black dark:hover:text-blue-200 text-sm font-medium hover:no-underline cursor-pointer" href="">{type.title}</a>
											</li>
										{/if}
									{/each}
								</ul>
							</div>
						</li>
					{/each}
				</div>
			</div>
			{/if}
		</nav>
	</div>
</header>
{/if}

<style>
.breadcrumbs li .breadcrumb {
  outline: none;
}

.breadcrumbs li .breadcrumb, .breadcrumbs li [itemprop=name] {
    display: flex;
    align-items: center;
    border-top-left-radius: 2px;
    border-bottom-left-radius: 2px;
}

.breadcrumbs.breadcrumbs--one li .breadcrumb {
    position: relative;
    margin-left: -1rem;
    margin-right: 1rem;
}

.breadcrumbs.breadcrumbs--one li [itemprop=name] {
    padding: 0.5rem 0.5rem 0.5rem 2rem;
}
.breadcrumbs.breadcrumbs--one li a.breadcrumb {
    text-decoration: none;
    /* background: linear-gradient(to right, white 0%, #f2f6f7 50%); */
    background-image: linear-gradient(to left, #3b82f6, #4387f5, #4b8cf5, #5390f4, #5b95f3);
}

.breadcrumbs.breadcrumbs--one li a.breadcrumb:hover, .breadcrumbs.breadcrumbs--one li a.breadcrumb:hover::after, .breadcrumbs.breadcrumbs--one li a.breadcrumb:focus, .breadcrumbs.breadcrumbs--one li a.breadcrumb:focus::after {
    background: #3369dd;
}

.breadcrumbs.breadcrumbs--one li a.breadcrumb::after {
    content: "";
	position: relative;
    display: block;
    width: 1.6rem;
    height: 1.55rem;
    z-index: 1;
    margin-right: -0.75rem;
    background-color: rgb(59 130 246);
    border: 0.15rem solid white;
    transform: rotate(45deg) skew(15deg, 15deg);
    transform-origin: center;
    clip-path: polygon(100% 0, 0 0, 100% 100%);
    border-radius: 0 3px 0 0;
}
.breadcrumbs.breadcrumbs--one li:first-child [itemprop=name] {
    padding-left: 1.5rem;
}
</style>
