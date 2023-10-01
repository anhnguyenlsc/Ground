<script>
    export let data;
    import axios from "axios";
	import { Storage } from '$lib/support';
    import { FormatDateTime } from '$lib/support.ts';

    let currentLocation = Storage.get('currentLocation');

    let type;
    let circleId;
    let circleName;
    let circle;
    let getOwner = Storage.get('authentication');
    
    let owner;
    let memberId;
    let currentAvatar;
	if (getOwner === undefined) {} 
	else if (getOwner.principal === undefined) {} 
	else {
		owner = getOwner.principal;
        memberId = getOwner.principal.id;
        currentAvatar = getOwner.principal.avatar;
	}


    let urlPage = '';
    if (typeof window !== 'undefined') {
        urlPage = window.location.pathname;
    }

    if (typeof localStorage !== 'undefined') {
        type = currentLocation.currentType;
    }

    function selectCircle(circleIdSelected, circleSelected) {
        circleId = circleIdSelected;
        circle = circleSelected;
        let curLoc = JSON.parse(localStorage.getItem('currentLocation'));
        curLoc.value.currentCircle = circle
        curLoc.value.currentCircleid = circleId;
        localStorage.setItem('currentLocation', JSON.stringify(curLoc));

        window.location.href = `/circles/${circleId}`;
    }

    async function joinCircle(memberId, circleId) {
        console.log("Gooooooooooooo");
        const join = {memberId, circleId};
        const response = await axios({
            method: "POST",
			url: "http://localhost:8080/api/authentication/circle/join",
			data: join
        })

        // if (response.data.status == "success") {
        //     window.location.reload();
        //     window.location.href = `${urlPage}/circles/${circleId}`;
        // }
    }
    function createCircle() {

    }

    function iconClick() {
        //navigate to post detail page
    }

    function clickScrollLeft() {

    }

    function clickScrollRight() {
        
    }
    let isOpen = false;
    let activeTab = 'Posts';

    let showDropdown = false;
    let viewTypes = ['Cards', 'Classic', 'Compact'];
    let selectedViewType = 'Cards';

</script>

{#if type !== 'undefined'}
    <div class="mt-2 flex px-16 mb-4">
        <h1 class="text-3xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-fuchsia-500 to-cyan-500">{type}</h1>
    </div>  
{/if}

<!-- Tabs buttons -->
<div class="flex mb-4 px-16 border-b border-gray-200">
    <button class="py-2 mr-10 rounded-md relative" class:font-bold={activeTab === 'Posts'} on:click={() => (activeTab = 'Posts')}>
        Posts
        {#if activeTab === 'Posts'}
            <span class="absolute bottom-0 left-0 w-full h-0.5 bg-blue-500 transition-all duration-300"/>
        {/if}
    </button>
    <button class="py-2 mr-10 rounded-md relative" class:font-bold={activeTab === 'Communities'} on:click={() => (activeTab = 'Communities')}>
        Communities
        {#if activeTab === 'Communities'}
            <span class="absolute bottom-0 left-0 w-full h-0.5 bg-blue-500 transition-all duration-300"/>
        {/if}
    </button>
</div>

<section class="mt-3 grid grid-cols-1 md:grid-cols-5 gap-5 mt-10"> 
    <div class="col-span-1 md:col-span-4 px-16">
        {#if activeTab === 'Posts'}
            <!-- Theme box -->
            <div class="t-box border p-3 rounded-2xl mb-4 shadow flex justify-between items-center" style="background-image: url('https://res.cloudinary.com/dzgdczkjk/image/upload/v1694933180/32432961_2206_w030_n003_400b_p1_400_r3drdm.jpg');">
                <p class="capitalized pl-2 inline-block text-base font-bold text-white align-middle">Posts related to {type}</p>

                <!-- small dropdown menu -->
                <div class="inline-block left-0">
                    <button class="p-1 bg-white rounded-full focus:outline-none hover:bg-gray-200 " on:click={() => showDropdown = !showDropdown}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="h-6 w-6 text-gray-800">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7"></path>
                        </svg>
                    </button>
                    
                    {#if showDropdown}
                        <div class="absolute mt-1 py-2 -ml-20 w-28 bg-white rounded-lg shadow-xl z-10 cursor-pointer">
                            {#each viewTypes as viewType (viewType)}
                                <!-- svelte-ignore a11y-click-events-have-key-events -->
                                <!-- svelte-ignore a11y-no-static-element-interactions -->
                                <div class= 'block px-4 py-2 text-gray-800 hover:bg-gray-200 hover:text-black'
                                    on:click={() => { selectedViewType = viewType; showDropdown = false; }}>{viewType}</div>
                            {/each}
                        </div>
                    {/if}
                </div>
            </div>

            <!-- Cards layout -->
            {#if selectedViewType === 'Cards'}
                <!-- Posts -->
                {#each data.summaries as circleItem}
                    {#each data.posts as postItem}
                        {#if circleItem.c_id === postItem.p_circleId && postItem.p_from == null}
                            <div class="border p-6 rounded-2xl mb-4">
                                <div class="flex items-center justify-between">
                                    <!-- Circle avatar -->
                                    <div class="gap-3.5 flex items-center ">
                                        <img src="{circleItem.c_avatar}" class="object-cover rounded-full w-10 h-10" alt="circle"/>
                                        <div class="flex flex-col">
                                            <p class="mb-2">
                                                <b class="capitalize"><a href="/circles/{postItem.p_circleId}" class="hover:underline hover:text-blue-500" on:click={selectCircle(postItem.p_circleId, postItem.p_circleName)}>{postItem.p_circleName}</a></b>
                                                <span class="text-gray-500 text-sm">&bull; Posted by <a href="" class="hover:underline hover:text-blue-500">{postItem.p_member.name}</a></span>
                                            </p>
                                            <p class="text-gray-400 text-xs">{FormatDateTime(postItem.p_createdAt)}</p>
                                        </div>
                                    </div>
                                </div>
                                <!-- Title Post -->
                                <div class="whitespace-pre-wrap mt-7 font-semibold text-xl">
                                    <p>{postItem.p_title}</p>
                                </div>
                                <!-- Content Post -->
                                <div class="whitespace-pre-wrap mt-2">
                                    <p>{postItem.p_content}</p>
                                </div>

                                <!-- Image/Video Post-->
                                {#if postItem.p_image !== null}
                                    <div class="flex justify-center border-b pb-4 flex-wrap">
                                        <img src="{postItem.p_image}" class="rounded-2xl w-full object-cover h-96 flex-auto" alt="post">
                                    </div>
                                {/if}

                                <!-- Reaction -->
                                <div class=" h-16 flex items-center justify-around">
                                    <div class="flex items-center gap-3">
                                        <svg width="20px" height="19px" viewBox="0 0 20 19" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                            class="hover:animate-bounce hover:cursor-pointer">
                                            <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                <g id="Square_Timeline" transform="translate(-312.000000, -746.000000)">
                                                    <g id="Post-1" transform="translate(280.000000, 227.000000)">
                                                        <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                            <g transform="translate(30.000000, 21.000000)" id="Comment">
                                                                <g>
                                                                    <g id="ic_comment-Component/icon/ic_comment">
                                                                        <g id="Comments">
                                                                            <polygon id="Path" points="0 0 24 0 24 25 0 25"></polygon>
                                                                            <g id="iconspace_Chat-3_25px"
                                                                                transform="translate(2.000000, 3.000000)" fill="#92929D">
                                                                                <path
                                                                                    d="M10.5139395,15.2840977 L6.06545155,18.6848361 C5.05870104,19.4544672 3.61004168,18.735539 3.60795568,17.4701239 L3.60413773,15.1540669 C1.53288019,14.6559967 0,12.7858138 0,10.5640427 L0,4.72005508 C0,2.11409332 2.10603901,0 4.70588235,0 L15.2941176,0 C17.893961,0 20,2.11409332 20,4.72005508 L20,10.5640427 C20,13.1700044 17.893961,15.2840977 15.2941176,15.2840977 L10.5139395,15.2840977 Z M5.60638935,16.5183044 L9.56815664,13.4896497 C9.74255213,13.3563295 9.955971,13.2840977 10.1754888,13.2840977 L15.2941176,13.2840977 C16.7876789,13.2840977 18,12.0671403 18,10.5640427 L18,4.72005508 C18,3.21695746 16.7876789,2 15.2941176,2 L4.70588235,2 C3.21232108,2 2,3.21695746 2,4.72005508 L2,10.5640427 C2,12.0388485 3.1690612,13.2429664 4.6301335,13.28306 C5.17089106,13.297899 5.60180952,13.7400748 5.60270128,14.2810352 L5.60638935,16.5183044 Z"
                                                                                    id="Path"></path>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </g>
                                                </g>
                                            </g>
                                        </svg>
                                        <div class="text-sm	">{postItem.p_comments.length} Comments</div>
                                    </div>

                                    <!-- Likes -->
                                    <!-- svelte-ignore a11y-no-static-element-interactions -->
                                    <div class="flex items-center gap-3">
                                        <!-- svelte-ignore a11y-click-events-have-key-events -->
                                        <!-- svelte-ignore missing-declaration -->
                                        <svg on:click={iconClick} xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-red-500 hover:cursor-pointer hover:animate-bounce" viewBox="0 0 20 20"
                                            fill="currentColor">
                                            <path fill-rule="evenodd"
                                                d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
                                                clip-rule="evenodd" />
                                        </svg>
                                        <div class="text-sm">{postItem.p_likes.length} Likes</div>
                                    </div>

                                    <!-- Share -->
                                    <!-- svelte-ignore a11y-click-events-have-key-events -->
                                    <!-- on:click={openShare} -->
                                    <div class="flex items-center gap-3">
                                        <svg width="22px" height="22px" viewBox="0 0 22 22" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                            class="hover:animate-bounce hover:cursor-pointer">
                                            <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                <g id="Square_Timeline" transform="translate(-636.000000, -745.000000)">
                                                    <g id="Post-1" transform="translate(280.000000, 227.000000)">
                                                        <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                            <g transform="translate(30.000000, 21.000000)" id="Share">
                                                                <g transform="translate(325.000000, 1.000000)">
                                                                    <g id="ic_Share-Component/icon/ic_Share">
                                                                        <g id="Share">
                                                                            <circle id="Oval" cx="12" cy="12" r="12"></circle>
                                                                            <g id="Group-24-Copy"
                                                                                transform="translate(12.000000, 12.000000) scale(-1, 1) translate(-12.000000, -12.000000) translate(1.000000, 1.000000)"
                                                                                fill="#92929D">
                                                                                <path
                                                                                    d="M4,0 C6.209139,0 8,1.790861 8,4 C8,4.1291298 7.99388117,4.25683047 7.98191762,4.38282788 L15.371607,7.98470389 C16.0745405,7.37145444 16.9938914,7 18,7 C20.209139,7 22,8.790861 22,11 C22,13.209139 20.209139,15 18,15 C16.9572434,15 16.0076801,14.6009919 15.2956607,13.9473263 L7.98384745,17.6380767 C7.99453877,17.7572882 8,17.8780063 8,18 C8,20.209139 6.209139,22 4,22 C1.790861,22 0,20.209139 0,18 C0,15.790861 1.790861,14 4,14 C5.37147453,14 6.58173814,14.690226 7.30236849,15.7422555 L14.2017356,12.2577203 C14.0708451,11.8622268 14,11.4393868 14,11 C14,10.5276126 14.0818865,10.0743509 14.2322392,9.65363512 L7.29274641,6.27172794 C6.57099412,7.31588608 5.36538874,8 4,8 C1.790861,8 0,6.209139 0,4 C0,1.790861 1.790861,0 4,0 Z M4,16 C2.8954305,16 2,16.8954305 2,18 C2,19.1045695 2.8954305,20 4,20 C5.1045695,20 6,19.1045695 6,18 C6,16.8954305 5.1045695,16 4,16 Z M18,9 C16.8954305,9 16,9.8954305 16,11 C16,12.1045695 16.8954305,13 18,13 C19.1045695,13 20,12.1045695 20,11 C20,9.8954305 19.1045695,9 18,9 Z M4,2 C2.8954305,2 2,2.8954305 2,4 C2,5.1045695 2.8954305,6 4,6 C5.1045695,6 6,5.1045695 6,4 C6,2.8954305 5.1045695,2 4,2 Z"
                                                                                    id="Combined-Shape"></path>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </g>
                                                </g>
                                            </g>
                                        </svg>
                                        <div class="text-sm">{postItem.p_share.length} Shares</div>
                                    </div>

                                    <!-- Save posts -->
                                    <!-- svelte-ignore a11y-click-events-have-key-events -->
                                    <!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
                                    <!-- svelte-ignore missing-declaration -->
                                    <!-- svelte-ignore a11y-no-static-element-interactions -->
                                    <!-- on:click={() => savePosts(postItem)} -->
                                    <div class="flex items-center gap-3">
                                        <svg width="17px" height="22px" viewBox="0 0 17 22" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                            class="hover:animate-bounce hover:cursor-pointer">
                                            <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                <g id="Square_Timeline" transform="translate(-787.000000, -745.000000)">
                                                    <g id="Post-1" transform="translate(280.000000, 227.000000)"> 
                                                        <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                            <g transform="translate(30.000000, 21.000000)" id="Saved">
                                                                <g transform="translate(473.000000, 1.000000)">
                                                                    <g id="ic_Saved-Component/icon/ic_Saved">
                                                                        <g id="Saved">
                                                                            <circle id="Oval" cx="12" cy="12" r="12"></circle>
                                                                            <g id="Group-13-Copy" transform="translate(5.000000, 2.000000)"
                                                                                fill="#92929D">
                                                                                <path
                                                                                    d="M2.85714286,-0.952380952 L12.1428571,-0.952380952 C14.246799,-0.952380952 15.952381,0.753200953 15.952381,2.85714286 L15.952381,18.2119141 C15.952381,19.263885 15.09959,20.116746 14.047619,20.116746 C13.6150601,20.116746 13.1953831,19.9694461 12.8576286,19.6992071 L7.5,15.4125421 L2.14237143,19.6992071 C1.32096217,20.3564207 0.122301512,20.2233138 -0.534912082,19.4019046 C-0.805151112,19.0641501 -0.952380952,18.644473 -0.952380952,18.2119141 L-0.952380952,2.85714286 C-0.952380952,0.753200953 0.753200953,-0.952380952 2.85714286,-0.952380952 Z M2.85714286,0.952380952 C1.80517191,0.952380952 0.952380952,1.80517191 0.952380952,2.85714286 L0.952380952,18.2119141 L6.31000952,13.9252491 C7.00569973,13.3686239 7.99430027,13.3686239 8.68999048,13.9252491 L14.047619,18.2119141 L14.047619,2.85714286 C14.047619,1.80517191 13.1948281,0.952380952 12.1428571,0.952380952 L2.85714286,0.952380952 Z"
                                                                                    id="Rectangle-92"></path>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </g>
                                                </g>
                                            </g>
                                        </svg>
                                        <div class="text-sm">Saved</div>
                                    </div>
                                </div>
                            </div>
                        {/if}
                    {/each}
                {/each}
            
            <!-- Classic layout-->
            {:else if selectedViewType === 'Classic'}
                {#each data.summaries as circleItem}
                    {#each data.posts as postItem}
                        {#if circleItem.c_id === postItem.p_circleId && postItem.p_from == null}
                            <div class="mb-5 rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
                                <div>
                                    <div class="flex items-start space-x-4 p-4 w-30 h-30">
                                        <!-- Image Post-->
                                        <img src="{postItem.p_image}" alt="{postItem.p_title}" class="w-40 h-40 rounded-md">
                                        <div>
                                            <!-- Title -->
                                            <h2 class="font-bold text-lg mb-2">{postItem.p_title}</h2>
                                    
                                            <p class="mb-2">
                                                <a href="/circles/{postItem.p_circleId}" class="font-semibold hover:underline hover:text-blue-500">{postItem.p_circleName}</a> 
                                                <span class="text-sm text-gray-500">
                                                    - Posted by <a href="" class="hover:underline hover:text-blue-500">{postItem.p_member.name}</a>
                                                </span>
                                            </p>
                                    
                                            <div class="flex items-center w-full h-auto space-x-2 mb-4">
                                                <p class="text-gray-400 text-xs">{FormatDateTime(postItem.p_createdAt)}</p>
                                            </div>
                                    
                                            <!-- Reaction -->
                                            <div class="flex">
                                                <!-- Comments -->
                                                <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                                    <svg width="20px" height="19px" viewBox="0 0 20 19" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                                        class="hover:animate-bounce hover:cursor-pointer">
                                                        <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                            <g id="Square_Timeline" transform="translate(-312.000000, -746.000000)">
                                                                <g id="Post-1" transform="translate(280.000000, 227.000000)">
                                                                    <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                                        <g transform="translate(30.000000, 21.000000)" id="Comment">
                                                                            <g>
                                                                                <g id="ic_comment-Component/icon/ic_comment">
                                                                                    <g id="Comments">
                                                                                        <polygon id="Path" points="0 0 24 0 24 25 0 25"></polygon>
                                                                                        <g id="iconspace_Chat-3_25px"
                                                                                            transform="translate(2.000000, 3.000000)" fill="#92929D">
                                                                                            <path
                                                                                                d="M10.5139395,15.2840977 L6.06545155,18.6848361 C5.05870104,19.4544672 3.61004168,18.735539 3.60795568,17.4701239 L3.60413773,15.1540669 C1.53288019,14.6559967 0,12.7858138 0,10.5640427 L0,4.72005508 C0,2.11409332 2.10603901,0 4.70588235,0 L15.2941176,0 C17.893961,0 20,2.11409332 20,4.72005508 L20,10.5640427 C20,13.1700044 17.893961,15.2840977 15.2941176,15.2840977 L10.5139395,15.2840977 Z M5.60638935,16.5183044 L9.56815664,13.4896497 C9.74255213,13.3563295 9.955971,13.2840977 10.1754888,13.2840977 L15.2941176,13.2840977 C16.7876789,13.2840977 18,12.0671403 18,10.5640427 L18,4.72005508 C18,3.21695746 16.7876789,2 15.2941176,2 L4.70588235,2 C3.21232108,2 2,3.21695746 2,4.72005508 L2,10.5640427 C2,12.0388485 3.1690612,13.2429664 4.6301335,13.28306 C5.17089106,13.297899 5.60180952,13.7400748 5.60270128,14.2810352 L5.60638935,16.5183044 Z"
                                                                                                id="Path"></path>
                                                                                        </g>
                                                                                    </g>
                                                                                </g>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </svg>
                                                    <div class="text-sm">{postItem.p_comments.length} Comments</div>
                                                </div>
                                                <!-- Likes -->
                                                <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                                    <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-red-500 hover:cursor-pointer hover:animate-bounce" viewBox="0 0 20 20"
                                                        fill="currentColor">
                                                        <path fill-rule="evenodd"
                                                            d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
                                                            clip-rule="evenodd" />
                                                    </svg>
                                                    <div class="text-sm">{postItem.p_likes.length} Likes</div>
                                                </div>
                                                <!-- Shares -->
                                                <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                                    <svg width="22px" height="22px" viewBox="0 0 22 22" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                                        class="hover:animate-bounce hover:cursor-pointer">
                                                        <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                            <g id="Square_Timeline" transform="translate(-636.000000, -745.000000)">
                                                                <g id="Post-1" transform="translate(280.000000, 227.000000)">
                                                                    <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                                        <g transform="translate(30.000000, 21.000000)" id="Share">
                                                                            <g transform="translate(325.000000, 1.000000)">
                                                                                <g id="ic_Share-Component/icon/ic_Share">
                                                                                    <g id="Share">
                                                                                        <circle id="Oval" cx="12" cy="12" r="12"></circle>
                                                                                        <g id="Group-24-Copy"
                                                                                            transform="translate(12.000000, 12.000000) scale(-1, 1) translate(-12.000000, -12.000000) translate(1.000000, 1.000000)"
                                                                                            fill="#92929D">
                                                                                            <path
                                                                                                d="M4,0 C6.209139,0 8,1.790861 8,4 C8,4.1291298 7.99388117,4.25683047 7.98191762,4.38282788 L15.371607,7.98470389 C16.0745405,7.37145444 16.9938914,7 18,7 C20.209139,7 22,8.790861 22,11 C22,13.209139 20.209139,15 18,15 C16.9572434,15 16.0076801,14.6009919 15.2956607,13.9473263 L7.98384745,17.6380767 C7.99453877,17.7572882 8,17.8780063 8,18 C8,20.209139 6.209139,22 4,22 C1.790861,22 0,20.209139 0,18 C0,15.790861 1.790861,14 4,14 C5.37147453,14 6.58173814,14.690226 7.30236849,15.7422555 L14.2017356,12.2577203 C14.0708451,11.8622268 14,11.4393868 14,11 C14,10.5276126 14.0818865,10.0743509 14.2322392,9.65363512 L7.29274641,6.27172794 C6.57099412,7.31588608 5.36538874,8 4,8 C1.790861,8 0,6.209139 0,4 C0,1.790861 1.790861,0 4,0 Z M4,16 C2.8954305,16 2,16.8954305 2,18 C2,19.1045695 2.8954305,20 4,20 C5.1045695,20 6,19.1045695 6,18 C6,16.8954305 5.1045695,16 4,16 Z M18,9 C16.8954305,9 16,9.8954305 16,11 C16,12.1045695 16.8954305,13 18,13 C19.1045695,13 20,12.1045695 20,11 C20,9.8954305 19.1045695,9 18,9 Z M4,2 C2.8954305,2 2,2.8954305 2,4 C2,5.1045695 2.8954305,6 4,6 C5.1045695,6 6,5.1045695 6,4 C6,2.8954305 5.1045695,2 4,2 Z"
                                                                                                id="Combined-Shape"></path>
                                                                                        </g>
                                                                                    </g>
                                                                                </g>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </svg>
                                                    <div class="text-sm">3 Shares</div>
                                                </div>
                                                <!-- Saveds -->
                                                <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                                    <svg width="17px" height="22px" viewBox="0 0 17 22" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                                        class="hover:animate-bounce hover:cursor-pointer">
                                                        <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                            <g id="Square_Timeline" transform="translate(-787.000000, -745.000000)">
                                                                <g id="Post-1" transform="translate(280.000000, 227.000000)"> 
                                                                    <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                                        <g transform="translate(30.000000, 21.000000)" id="Saved">
                                                                            <g transform="translate(473.000000, 1.000000)">
                                                                                <g id="ic_Saved-Component/icon/ic_Saved">
                                                                                    <g id="Saved">
                                                                                        <circle id="Oval" cx="12" cy="12" r="12"></circle>
                                                                                        <g id="Group-13-Copy" transform="translate(5.000000, 2.000000)"
                                                                                            fill="#92929D">
                                                                                            <path
                                                                                                d="M2.85714286,-0.952380952 L12.1428571,-0.952380952 C14.246799,-0.952380952 15.952381,0.753200953 15.952381,2.85714286 L15.952381,18.2119141 C15.952381,19.263885 15.09959,20.116746 14.047619,20.116746 C13.6150601,20.116746 13.1953831,19.9694461 12.8576286,19.6992071 L7.5,15.4125421 L2.14237143,19.6992071 C1.32096217,20.3564207 0.122301512,20.2233138 -0.534912082,19.4019046 C-0.805151112,19.0641501 -0.952380952,18.644473 -0.952380952,18.2119141 L-0.952380952,2.85714286 C-0.952380952,0.753200953 0.753200953,-0.952380952 2.85714286,-0.952380952 Z M2.85714286,0.952380952 C1.80517191,0.952380952 0.952380952,1.80517191 0.952380952,2.85714286 L0.952380952,18.2119141 L6.31000952,13.9252491 C7.00569973,13.3686239 7.99430027,13.3686239 8.68999048,13.9252491 L14.047619,18.2119141 L14.047619,2.85714286 C14.047619,1.80517191 13.1948281,0.952380952 12.1428571,0.952380952 L2.85714286,0.952380952 Z"
                                                                                                id="Rectangle-92"></path>
                                                                                        </g>
                                                                                    </g>
                                                                                </g>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </svg>
                                                    <div class="text-sm">Saved</div>
                                                </div>

                                                 <!-- More -->
                                                 <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 hover:animate-bounce">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M6.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0zM12.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0zM18.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0z" />
                                                    </svg>  
                                                    <div class="text-sm">More</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        {/if}
                    {/each}
                {/each}
            <!-- Compact layout-->    
            {:else if selectedViewType === 'Compact'}
                {#each data.summaries as circleItem}
                    {#each data.posts as postItem}
                        {#if circleItem.c_id === postItem.p_circleId && postItem.p_from == null}
                            <div class="mb-5 rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
                                <div class="flex justify-between items-center space-x-4 p-2 w-30 h-30">
                                    <!-- Type of post and Title -->
                                    <div class="flex justify-between items-center space-x-4">
                                        <div class="rounded-md w-14 h-14 cursor-pointer hover:bg-gray-300 flex justify-center items-center">
                                            <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                                            viewBox="0 0 503.689 503.689" xml:space="preserve" class="w-10 h-10">
                                            <path style="fill:#E4E7E7;" d="M16.502,135.838L396.564,34.225c11.815-3.151,24.418,3.938,27.569,15.754l75.225,279.237
                                            c3.151,11.815-3.938,24.025-16.148,27.175L103.149,458.004c-11.815,3.151-24.418-3.938-27.569-15.754L0.749,163.013
                                            C-2.402,151.198,4.687,138.989,16.502,135.838z"/>
                                            <path style="fill:#208DB2;" d="M48.404,158.287L380.81,69.672c9.058-2.363,18.511,3.151,21.268,12.209l62.228,231.188
                                            c2.363,9.058-3.151,18.511-12.209,20.874L119.69,422.558c-9.058,2.363-18.511-3.151-21.268-12.209L36.195,179.161
                                            C33.438,170.102,38.952,160.65,48.404,158.287z"/>
                                            <path style="fill:#F3F3F3;" d="M85.425,131.899H481.24c12.603,0,22.449,10.24,22.449,22.449v293.415
                                            c0,12.603-10.24,22.449-22.449,22.449H85.425c-12.603,0-22.449-10.24-22.449-22.449V154.349
                                            C62.976,142.139,73.216,131.899,85.425,131.899z"/>
                                            <path style="fill:#A3E0F5;" d="M111.813,163.407h343.828c9.452,0,17.329,7.877,17.329,17.329V421.77
                                            c0,9.452-7.877,17.329-17.329,17.329H111.813c-9.452,0-17.329-7.877-17.329-17.329V180.736
                                            C94.484,171.284,101.967,163.407,111.813,163.407z"/>
                                            <path style="fill:#EFC75E;" d="M94.484,180.736v84.283c3.938,0.394,7.877,0.788,11.815,0.788c50.018,0,90.585-40.566,90.585-90.585
                                            c0-3.938-0.394-7.877-0.788-11.815h-84.283C101.967,163.407,94.484,171.284,94.484,180.736z"/>
                                            <path style="fill:#3DB39E;" d="M123.235,356.392c-9.846,0-19.692,0.788-28.751,1.969v63.409c0,9.452,7.877,17.329,17.329,17.329
                                            h131.151c0.788-3.545,1.182-6.695,1.182-10.24C244.145,388.687,190.189,356.392,123.235,356.392z"/>
                                            <path style="fill:#4BC2AD;" d="M397.745,320.945c-107.914,0-195.742,52.775-196.923,118.154h254.425
                                            c9.452,0,17.329-7.877,17.329-17.329v-91.766C449.339,324.49,424.133,320.945,397.745,320.945z"/>
                                            <g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g>
                                            </svg>
                                        </div>
                            
                                        <div>
                                            <!-- Title -->
                                            <h2 class="font-bold text-sm mb-2">{postItem.p_title}</h2>
                                            <p class="mb-2 text-sm">
                                                <a href="/circles/{postItem.p_circleId}" class="font-semibold hover:underline hover:text-blue-500">{postItem.p_circleName}</a> 
                                                <span class="text-xs text-gray-500">
                                                    - Posted by <a href="" class="hover:underline hover:text-blue-500">{postItem.p_member.name}</a>
                                                </span>
                                            </p>
                                            <div class="flex items-center w-full h-auto space-x-2">
                                                <p class="text-gray-400 text-xs">{FormatDateTime(postItem.p_createdAt)}</p>
                                            </div>
                                        </div>
                                    </div>
                            
                                    <!-- Reaction -->
                                    <div class="flex flex-col justify-start items-start">
                                        <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                            <svg viewBox="0 0 20 19" version="1.1" xmlns="http://www.w3.org/2000/svg"
                                                class="w-5 h-6 hover:animate-bounce hover:cursor-pointer">
                                                <g id="?-Social-Media" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                    <g id="Square_Timeline" transform="translate(-312.000000, -746.000000)">
                                                        <g id="Post-1" transform="translate(280.000000, 227.000000)">
                                                            <g id="Post-Action" transform="translate(0.000000, 495.000000)">
                                                                <g transform="translate(30.000000, 21.000000)" id="Comment">
                                                                    <g>
                                                                        <g id="ic_comment-Component/icon/ic_comment">
                                                                            <g id="Comments">
                                                                                <polygon id="Path" points="0 0 24 0 24 25 0 25"></polygon>
                                                                                <g id="iconspace_Chat-3_25px"
                                                                                    transform="translate(2.000000, 3.000000)" fill="#92929D">
                                                                                    <path
                                                                                        d="M10.5139395,15.2840977 L6.06545155,18.6848361 C5.05870104,19.4544672 3.61004168,18.735539 3.60795568,17.4701239 L3.60413773,15.1540669 C1.53288019,14.6559967 0,12.7858138 0,10.5640427 L0,4.72005508 C0,2.11409332 2.10603901,0 4.70588235,0 L15.2941176,0 C17.893961,0 20,2.11409332 20,4.72005508 L20,10.5640427 C20,13.1700044 17.893961,15.2840977 15.2941176,15.2840977 L10.5139395,15.2840977 Z M5.60638935,16.5183044 L9.56815664,13.4896497 C9.74255213,13.3563295 9.955971,13.2840977 10.1754888,13.2840977 L15.2941176,13.2840977 C16.7876789,13.2840977 18,12.0671403 18,10.5640427 L18,4.72005508 C18,3.21695746 16.7876789,2 15.2941176,2 L4.70588235,2 C3.21232108,2 2,3.21695746 2,4.72005508 L2,10.5640427 C2,12.0388485 3.1690612,13.2429664 4.6301335,13.28306 C5.17089106,13.297899 5.60180952,13.7400748 5.60270128,14.2810352 L5.60638935,16.5183044 Z"
                                                                                        id="Path"></path>
                                                                                </g>
                                                                            </g>
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </g>
                                                </g>
                                            </svg>
                                            <div class="text-sm">{postItem.p_comments.length} Comments</div>
                                        </div>

                                        <!-- Likes -->
                                        <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-5 text-red-500 hover:cursor-pointer hover:animate-bounce" viewBox="0 0 20 20"
                                                fill="currentColor">
                                                <path fill-rule="evenodd"
                                                    d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
                                                    clip-rule="evenodd" />
                                            </svg>
                                            <div class="text-sm ">{postItem.p_likes.length} Likes</div>
                                        </div>

                                        <!-- More -->
                                        <div class="flex items-center gap-3 hover:bg-gray-200 p-1 rounded-lg hover:cursor-pointer">
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 hover:animate-bounce">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="M6.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0zM12.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0zM18.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0z" />
                                            </svg>  
                                            <div class="text-sm">More</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
                        {/if}
                    {/each}
                {/each}
            {/if}
            
        
        {:else if activeTab === "Communities"}
            <!-- Theme box -->
            <div class="t-box border p-3 rounded-2xl mb-4 shadow flex justify-between items-center" style="background-image: url('https://res.cloudinary.com/dzgdczkjk/image/upload/v1694933180/32432961_2206_w030_n003_400b_p1_400_r3drdm.jpg');">
                <p class="capitalized pl-2 inline-block text-base font-bold text-white align-middle">Communities related to {type}</p>

                <div class="inline-block left-0">
                    <div class="mt-2 w-6 h-6"></div>                    
                </div>
            </div>
            <div class="border p-6 rounded-2xl mb-4">
                {#each data.summaries as circleItem}
                    <div class="flex items-center justify-between mb-6">
                        <!-- Circle avatar -->
                        <div class="gap-3.5 flex">
                            <img src="{circleItem.c_avatar}" class="object-cover rounded-full w-14 h-14 flex-shrink-0" alt="circle"/>
                            <div class="flex flex-col -mt-1">
                                <!-- on:click={selectCircle(postItem.p_circleId, postItem.p_circleName)} -->
                                <p class="capitalize text-base font-semibold"><a href="/circles/{circleItem.c_id}" class="hover:underline hover:text-blue-400">{circleItem.c_name}</a></p>
                                <p class="text-gray-600 text-xs pb-1 font-medium">{circleItem.c_membersCounts.length} member</p>
                                <p class="text-gray-500 text-xs mr-6 pb-2">{circleItem.c_des}{circleItem.c_des}</p>
                                <a href="/circles/{circleItem.c_id}" class="w-fit text-sm font-semibold text-blue-600 hover:underline">View</a>
                            </div>
                        </div>
                        
                        <!-- Join button -->
                        <button class="bg-blue-600 disabled:bg-gray-300 disabled:border-gray-500 r-0 hover:bg-blue-500 text-white font-bold py-1 px-2.5 border-b-4 border-blue-700 hover:shadow-sm transition-all duration-300 rounded">Join</button>
                    </div>
                {/each}
            </div>
                        
        {/if}
        
    </div>
    
    <div class="col-span-1 md:col-span-1">
        <!-- Top communities -->
        <div class="rounded-xl mb-4 shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
            <div class="bg-white/10 col-span-3 rounded-lg p-4">
                <h1 class="pb-3">Top communities</h1>
                <hr class="py-3 border-slate-200">
                <a href="#" class="flex flex-col space-y-2 md:space-y-0 md:flex-row mb-5 items-center md:space-x-2 hover:bg-white/10 group transition duration-150 ease-linear rounded-lg group w-full">
                    <div>
                        <img class="rounded-full w-8 h-8 object-cover" src="https://images.igdb.com/igdb/image/upload/t_cover_big/co2emk.png" alt="">
                    </div>
                    <div class="flex flex-col">
                        <!-- Circle name -->
                        <p class="font-normal text-sm font-semibold hover:text-blue-500 leading-4">Krampus is Home</p>
                        <!-- Members -->
                        <div class="flex items-center gap-2 mt-1">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4 text-slate-400">
                                <path d="M10 9a3 3 0 100-6 3 3 0 000 6zM6 8a2 2 0 11-4 0 2 2 0 014 0zM1.49 15.326a.78.78 0 01-.358-.442 3 3 0 014.308-3.516 6.484 6.484 0 00-1.905 3.959c-.023.222-.014.442.025.654a4.97 4.97 0 01-2.07-.655zM16.44 15.98a4.97 4.97 0 002.07-.654.78.78 0 00.357-.442 3 3 0 00-4.308-3.517 6.484 6.484 0 011.907 3.96 2.32 2.32 0 01-.026.654zM18 8a2 2 0 11-4 0 2 2 0 014 0zM5.304 16.19a.844.844 0 01-.277-.71 5 5 0 019.947 0 .843.843 0 01-.277.71A6.975 6.975 0 0110 18a6.974 6.974 0 01-4.696-1.81z" />
                            </svg>                          
                            <span class="text-xs text-slate-400">100k members</span>
                        </div>
                        
                    </div>
                </a>
                <a href="#" class="flex flex-col space-y-2 md:space-y-0 md:flex-row mb-5 items-center md:space-x-2 hover:bg-white/10 group transition duration-150 ease-linear rounded-lg group w-full">
                    <div>
                        <img class="rounded-full w-8 h-8 object-cover" src="https://images.igdb.com/igdb/image/upload/t_cover_big/co1zil.png" alt="">
                    </div>
                    <div class="flex flex-col">
                        <!-- Circle name -->
                        <p class="font-normal text-sm font-semibold hover:text-blue-500 leading-4">Secret Neighbor</p>
                        <!-- Members -->
                        <div class="flex items-center gap-2 mt-1">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4 text-slate-400">
                                <path d="M10 9a3 3 0 100-6 3 3 0 000 6zM6 8a2 2 0 11-4 0 2 2 0 014 0zM1.49 15.326a.78.78 0 01-.358-.442 3 3 0 014.308-3.516 6.484 6.484 0 00-1.905 3.959c-.023.222-.014.442.025.654a4.97 4.97 0 01-2.07-.655zM16.44 15.98a4.97 4.97 0 002.07-.654.78.78 0 00.357-.442 3 3 0 00-4.308-3.517 6.484 6.484 0 011.907 3.96 2.32 2.32 0 01-.026.654zM18 8a2 2 0 11-4 0 2 2 0 014 0zM5.304 16.19a.844.844 0 01-.277-.71 5 5 0 019.947 0 .843.843 0 01-.277.71A6.975 6.975 0 0110 18a6.974 6.974 0 01-4.696-1.81z" />
                            </svg>                          
                            <span class="text-xs text-slate-400">83k members</span>
                        </div>
                        
                    </div>
                </a>
            </div>
        </div>
    </div>
    

</section>


<style>
.t-box {
    background-size: cover;
    -webkit-animation: slidein 60s;
    animation: slidein 60s;

    -webkit-animation-fill-mode: forwards;
    animation-fill-mode: forwards;

    -webkit-animation-iteration-count: infinite;
    animation-iteration-count: infinite;

    -webkit-animation-direction: alternate;
    animation-direction: alternate;              
}


@-webkit-keyframes slidein {
    from {background-position: top; background-size:3000px; }
    to {background-position: -100px 0px;background-size:2750px;}
}

@keyframes slidein {
    from {background-position: top;background-size:3000px; }
    to {background-position: -100px 0px;background-size:2750px;}
}

</style>