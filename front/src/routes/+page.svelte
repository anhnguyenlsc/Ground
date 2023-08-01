<script>
	import Banner from "../lib/components/Banner.svelte"
	import Ads from "../lib/components/Ads.svelte"
	import Slider from "../lib/components/Slider.svelte"
</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="" />
</svelte:head>

<div class="mx-auto max-w-6xl">
	<section>
		<!-- Left -->
		<div class="flex lg:space-x-10 mt-5">
			<div class="w-full min-w-0 flex-auto lg:static lg:max-h-full lg:overflow-visible">
				<!--Welcome banner-->
				<Banner />

				<!-- Post Content -->
				<div class="min-w-0 flex-auto">
					<div class="my-12 flex flex-col gap-x-10 gap-y-10 sm:my-8 md:flex-row">
						<div class="w-full">
							<!-- Header -->
							<a class="relative mb-2 text-gray-900 no-underline dark:text-gray-100" href="">
								<div class="group relative">
									<div class="h-16 w-full rounded-xl bg-blue-50 dark:bg-gray-700">
										<div class="h-full rounded-xl bg-right-bottom opacity-10"></div>
									</div>
									<div class="absolute top-0 flex h-full w-full items-center px-8 hover:no-underline dark:hover:no-underline">
										<span class="w-full font-semibold hover:text-blue-500 hover:no-underline group-hover:text-blue-500 dark:hover:text-blue-200 dark:hover:no-underline dark:group-hover:text-blue-200 sm:text-lg">Hit</span>
									</div>
								</div>
							</a>
							<!-- Comments/Posts -->
							<div>
								<ul class="divide-y divide-gray-500/30 dark:divide-gray-500/70">
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/03a56d07b93281afd86378adf1e086d9?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">왓에버아두</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>10</span>
														<span>·</span>
														<span>약 2시간 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 	shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">1</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">파이썬을 백엔드 쪽에서 활용하는 회사는 어떤쪽으로 활용하는 걸까요?</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/b7e3d18b6afba8b3f16a3b153eea12af?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">ICE민트초코</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>11</span>
														<span>·</span>
														<span>약 2시간 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">2</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">비전공자 고민입니다!</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="https://avatars.githubusercontent.com/u/31244245?v=4" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">Choiyos</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>25</span>
														<span>·</span>
														<span>약 1시간 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">0</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">C# 엑셀 조작 중 병합된 셀에 붙여넣을때 "병합된 셀에서는 실행할 수 없습니다" 오류</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="https://ssl.pstatic.net/static/pwe/address/img_profile.png" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">IlIlIlIlIlIllllI</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>105</span>
														<span>·</span>
														<span>약 1시간 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">0</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">안드로이드 workmanager 무한루프 가능한가요?</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/381f320d7c9d6742c9f5207a52aea8bb?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">퍼불리샤</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>11</span>
														<span>·</span>
														<span>약 1시간 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">1</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">5</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">개발자 이직 조언 부탁드립니다!!</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="w-full">
							<!-- Header -->
							<a class="relative mb-2 text-gray-900 no-underline dark:text-gray-100" href="">
								<div class="group relative">
									<div class="h-16 w-full rounded-xl bg-blue-50 dark:bg-gray-700">
										<div class="h-full rounded-xl bg-bottom opacity-10"></div>
									</div>
									<div class="absolute top-0 flex h-full w-full items-center px-8 hover:no-underline dark:hover:no-underline">
										<span class="w-full font-semibold hover:text-blue-500 hover:no-underline group-hover:text-blue-500 dark:hover:text-blue-200 dark:hover:no-underline dark:group-hover:text-blue-200 sm:text-lg">Trend</span>
									</div>
								</div>
							</a>
							<!-- Comments/Posts -->
							<div>
								<ul class="divide-y divide-gray-500/30 dark:divide-gray-500/70">
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/37c29d336cf0f777fa3f0fae4b7efd35?d=identicon&amp;s=96" alt="프로필 사진"></a>
													<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">Troubadour</a>
													<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
														<span class="mr-0.5">·</span>
														<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
															<title>bolt icon</title>
															<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
														</svg>
														<span class="space-x-1">
															<span>20</span>
															<span>·</span>
															<span>약 2시간 전</span>
														</span>
													</div>
												</div>
												<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
														</svg>
														<span class="text-xs font-normal sm:text-sm">0</span>
													</div>
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
														</svg>
														<span class="ml-0.5 text-xs font-normal sm:text-sm">2</span>
													</div>
												</div>
											</div>
											<div>
												<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">개발자인데 기획,디자인,퍼블리셔,구축,개발을 담당하고있습니다..</a>
											</div>
										</li>
										<li class="py-4 last:pb-0">
											<div class="mb-2 flex">
												<div class="flex flex-1 items-center space-x-1">
													<a href="">
														<img class="h-5 w-5 rounded-full" src="" alt="프로필 사진">
													</a>
													<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">로빈은나무에서풋사과를따지</a>
													<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
														<span class="mr-0.5">·</span>
														<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
															<title>bolt icon</title>
															<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
														</svg>
														<span class="space-x-1">
															<span>1.2k</span>
															<span>·</span>
															<span>약 1시간 전</span>
														</span>
													</div>
												</div>
												<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
														</svg>
														<span class="text-xs font-normal sm:text-sm">1</span>
													</div>
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
														</svg>
														<span class="ml-0.5 text-xs font-normal sm:text-sm">2</span>
													</div>
												</div>
											</div>
											<div>
												<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">너무 공부하고싶은게 많네요</a>
											</div>
										</li>
										<li class="py-4 last:pb-0">
											<div class="mb-2 flex">
												<div class="flex flex-1 items-center space-x-1">
													<a href="">
														<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/200abb6bce27f5d19ec8ca50f4dc6b71?d=identicon&amp;s=96" alt="프로필 사진">
													</a>
													<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">etc</a>
													<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
														<span class="mr-0.5">·</span>
														<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
															<title>bolt icon</title>
															<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
														</svg>
														<span class="space-x-1">
															<span>762</span>
															<span>·</span>
															<span>38분 전</span>
														</span>
													</div>
												</div>
												<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
														</svg>
														<span class="text-xs font-normal sm:text-sm">0</span>
													</div>
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
														</svg>
														<span class="ml-0.5 text-xs font-normal sm:text-sm">4</span>
													</div>
												</div>
											</div>
											<div>
												<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">코무 자동차 보험 궁금증</a>
											</div>
										</li>
										<li class="py-4 last:pb-0">
											<div class="mb-2 flex">
												<div class="flex flex-1 items-center space-x-1">
													<a href="">
														<img class="h-5 w-5 rounded-full" src="https://ssl.pstatic.net/static/pwe/address/img_profile.png" alt="프로필 사진">
													</a>
													<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">쉽지않네이거</a>
													<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
														<span class="mr-0.5">·</span>
														<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
															<title>bolt icon</title>
															<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
														</svg>
														<span class="space-x-1">
															<span>18</span>
															<span>·</span>
															<span>37분 전</span>
														</span>
													</div>
												</div>
												<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
														</svg>
														<span class="text-xs font-normal sm:text-sm">-1</span>
													</div>
													<div class="flex flex-1 items-center">
														<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
															<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
														</svg>
														<span class="ml-0.5 text-xs font-normal sm:text-sm">0</span>
													</div>
												</div>
											</div>
											<div>
												<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">[문화체육관광부] 공공데이터 만족도 및 수요조사</a>
											</div>
										</li>
										<li class="py-4 last:pb-0">
											<div class="mb-2 flex"><div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="https://lh3.googleusercontent.com/a/AAcHTtcxOL7EzJFHMwwomqKOXPpmJANN9S5HaIOUB6JYxqYu=s96-c" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">열심히 해보자</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>18</span>
														<span>·</span>
														<span>26분 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">1</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">11</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">미래의 선배님들 여기 과정 한번만 봐주실 수 있을까요?</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="my-8 flex flex-col gap-x-10 gap-y-10 md:flex-row">
						<div class="w-full">
							<!-- Header -->
							<a class="mb-2 text-gray-900 no-underline dark:text-gray-100" href="">
								<div class="relative mb-2">
									<div class="relative">
										<div class="h-16 w-full rounded-xl bg-blue-50 dark:bg-gray-700">
											<div class="h-full rounded-xl bg-right-top opacity-10"></div>
										</div>
										<div class="absolute top-0 flex h-full w-full items-center px-8">
											<span class="w-full font-semibold hover:text-blue-500 hover:no-underline group-hover:text-blue-500 dark:hover:text-blue-200 dark:hover:no-underline dark:group-hover:text-blue-200 sm:text-lg">Chemistry</span>
										</div>
									</div>
								</div>
							</a>
							<!-- Comments/Posts -->
							<div>
								<ul class="divide-y divide-gray-500/30 dark:divide-gray-500/70">
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/4c403b2c49cbd4c7b62cc5b375d7cdb4?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">우루부루구루</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>5.1k</span>
														<span>·</span>
														<span>1일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">20</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">16</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">비전공자 나이 많은 분에게 현실적인 조언</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">표정이밝은사람</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>364</span>
														<span>·</span>
														<span>3일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">18</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">7</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">최근 여기서 게시글을 잘 안쓰게 된 이유</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/4c403b2c49cbd4c7b62cc5b375d7cdb4?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">우루부루구루</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>5.1k</span>
														<span>·</span>
														<span>3일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">11</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">14</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">우리 업계 프리 존재 이유.</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/33e27ebae103edc74a4fc67f8ff0358c?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">yeori</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>6.3k</span>
														<span>·</span>
														<span>2일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">9</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">22</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">내년 최저임금이 결정됐습니다.</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/4c403b2c49cbd4c7b62cc5b375d7cdb4?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">우루부루구루</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>5.1k</span>
														<span>·</span>
														<span>5일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">8</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">9</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">인력소개업체 사장님들은 무서우신 분들입니다.</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="w-full">
							<!-- Header -->
							<a class="mb-2 text-gray-900 no-underline dark:text-gray-100" href="">
								<div class="relative mb-2">
									<div class="relative">
										<div class="h-16 w-full rounded-xl bg-blue-50 dark:bg-gray-700">
											<div class="h-full rounded-xl bg-right-top opacity-10"></div>
										</div>
										<div class="absolute top-0 flex h-full w-full items-center px-8">
											<span class="w-full font-semibold hover:text-blue-500 hover:no-underline group-hover:text-blue-500 dark:hover:text-blue-200 dark:hover:no-underline dark:group-hover:text-blue-200 sm:text-lg">Realtime</span>
										</div>
									</div>
								</div>
							</a>
							<!-- Comments/Posts -->
							<div>
								<ul class="divide-y divide-gray-500/30 dark:divide-gray-500/70">
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="https://lh3.googleusercontent.com/a/AAcHTtcxJecGIAknSQ3o5ybSsCZJqlr8eEujvpmYE0alkB_5=s96-c" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">kimchumji</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>16</span>
														<span>·</span>
														<span>3일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">0</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">하나의 식?에서 같은 변수를 왜 사용하는 건가요?</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/60161ea0a5317843c78b0c062e7badb7?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">오리의꿈</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>21</span>
														<span>·</span>
														<span>3일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">0</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">큰일습니다..도와주세요..깃 저장소 다날라갔어요 ㅠㅠㅠ</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="https://ssl.pstatic.net/static/pwe/address/img_profile.png" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">낭낭이</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>250</span>
														<span>·</span>
														<span>2일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">0</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">인공지능이 개발자를 잡아먹는 시대가 언제올까요??</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/36e198dc914418ba29802fa3f070c003?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">백껜개발자</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>452</span>
														<span>·</span>
														<span>5일 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">20</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">신입은 보안때문에 프로젝트 코드를 보기 힘든가요?</a>
										</div>
									</li>
									<li class="py-4 last:pb-0">
										<div class="mb-2 flex">
											<div class="flex flex-1 items-center space-x-1">
												<a href="">
													<img class="h-5 w-5 rounded-full" src="//www.gravatar.com/avatar/6b62982a9c88ab14663d9f624e8568bf?d=identicon&amp;s=96" alt="프로필 사진">
												</a>
												<a class="inline pl-1 text-xs font-medium text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200 sm:text-sm" href="">freeeeeedom</a>
												<div class="text-xs font-normal leading-5 text-gray-700 dark:text-gray-300 sm:text-sm">
													<span class="mr-0.5">·</span>
													<svg class="inline h-3 w-3" width="9" height="12" viewBox="0 1 9 12" fill="none" xmlns="http://www.w3.org/2000/svg">
														<title>bolt icon</title>
														<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z" fill="currentColor"></path>
													</svg>
													<span class="space-x-1">
														<span>15</span>
														<span>·</span>
														<span>약 12시간 전</span>
													</span>
												</div>
											</div>
											<div class="flex space-x-2 text-gray-700 dark:text-gray-300">
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9"></path>
													</svg>
													<span class="text-xs font-normal sm:text-sm">0</span>
												</div>
												<div class="flex flex-1 items-center">
													<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 shrink-0">
														<path stroke-linecap="round" stroke-linejoin="round" d="M8.625 9.75a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375m-13.5 3.01c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.184-4.183a1.14 1.14 0 01.778-.332 48.294 48.294 0 005.83-.498c1.585-.233 2.708-1.626 2.708-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"></path>
													</svg>
													<span class="ml-0.5 text-xs font-normal sm:text-sm">12</span>
												</div>
											</div>
										</div>
										<div>
											<a class="line-clamp-1 w-fit break-all text-sm font-semibold text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200 sm:text-base sm:leading-5" href="">스타트업에서 자바를 안쓰는 경우는 왜일까요?</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Right -->
			<Ads />
		</div>
	</section>

	<!-- Slider -->
	<Slider />
</div>
