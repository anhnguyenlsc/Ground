<script lang="ts">
    export let data;
    import axios from "axios";
    import { FormatDate, FormatDateTime } from '$lib/support.ts';
    import type { Comment, Circle, Post, Collection } from '$lib/domain/index.ts';
    import { CircleRules } from "$lib/domain/index.ts";
    import { Storage } from '$lib/support.ts';
    import {fade, slide} from 'svelte/transition';
	import { onMount } from "svelte";

    // @ts-ignore
	let comment: Comment = {};
    // @ts-ignore
    let collection: Collection = {};
    let shareContentText = '';
    let picture = null;
    let file = null;
    let urlPage = '';
    let commentText = '';
    let comments = [];
    let postlikes = [];
    let likesCount = 0;
    let currentAvatar = '';
    let memberId: any;

    let createSubCir = false;
    let share = false;

    let getOwner = Storage.get('authentication');
    let currentLocation = Storage.get('currentLocation');

    let isJoined: any;
    let circleMembersCount = [];
    
	let owner: any;
	if (getOwner === undefined) {} 
    //@ts-ignore 
	else if (getOwner.principal === undefined) {} 
	else {
        //@ts-ignore
		owner = getOwner.principal
        //@ts-ignore
        currentAvatar = getOwner.principal.avatar
        //@ts-ignore
        memberId = getOwner.principal.id
	}

    let currentCircle = '';
    let currentCircleId: any;

    if (typeof localStorage !== 'undefined') {
        //@ts-ignore
        currentCircle = currentLocation.currentCircle;
        //@ts-ignore
        currentCircleId = currentLocation.currentCircleid;
    }

    function createCircle() {
        createSubCir = !createSubCir;
    }

    // @ts-ignore
	let circle: Circle = {
		rules: {}
	};


    let step = 0;
	let canGoNext = false;

    $: {
		if (step == 0) {
			canGoNext = true;
		}
		else if (step == 1) {
			canGoNext = true;
		}
	}

    const previous = () => {
		if (step == 0) {createSubCir = !createSubCir;}
		else {step--;}
	}

	async function next() {
		circle.owner = owner;
        circle.parent = Number(currentCircleId);
        circle.rules = data.details[0].rules;

		if (step == 1) {
				const response = await axios({
				method: "POST",
				url: "http://localhost:8080/api/authentication/circle/insert",
				data: circle
			})

			if (response.data.status == 'success') {
                console.log(response.data.status);
				location.reload();
			}
		}
		else {step++;}
	}

    function bannerSelected(e: any) {
		const image = e.target.files[0];

		const reader = new FileReader();
		reader.readAsDataURL(image);
		reader.onload = () => (circle.banner = reader.result as string);

		// console.log('Image file reader: ' + image);
	}

	function avatarSelected(e: any) {
		const image = e.target.files[0];

		const reader = new FileReader();
		reader.readAsDataURL(image);
		reader.onload = () => (circle.avatar = reader.result as string);

		// console.log('Avatar file reader: ' + image);
	}

    const submitForm = () => {
            if (typeof window !== 'undefined') {
            urlPage = window.location.pathname;
            window.location.href = urlPage + '/submitPost';
        }
    }

    async function handleLikeClick() {
        likesCount += 1;

        let post_id = data.posts[0].id;
        let member_id = owner.id;
        const response = await axios.post(`http://localhost:8080/api/authentication/post/like?id=${post_id}&memberId=${member_id}`);
    }


    onMount(async () => {
        circleMembersCount =  data.details[0].membersCounts;
        isJoined = circleMembersCount[0].id.includes(memberId);

        const postApiComment = 'http://localhost:8080/api/authentication/post/comment/getComments/' + data.posts[0].id;
        const postApi = 'http://localhost:8080/api/authentication/post/getPost/' + data.posts[0].id;

		const response = await axios({
			method: "GET",
			url: postApiComment,
		})

        const postComment = response.data;
        comments = postComment.map((com: { content: any; member: { name: any; avatar: any; }; }) => ({content: com.content, memName: com.member.name, memAvt: com.member.avatar}))
        // console.log(comments);

        const responsePost = await axios.get(postApi);
        const postLike = responsePost.data;

        postlikes = postLike.map((plike: { likes: any; }) => ({likes: plike.likes}))
        likesCount = postlikes[0].likes.length;
    });

    let showReplyInput = '';

    async function handleCommentClick() {
        comment.member = owner;
        comment.content = commentText;
        comment.postId = data.posts[0].id;
        comments = [...comments, { memAvt:currentAvatar, memName: owner.name, content: commentText }];

        commentText = '';

        const response = await axios({
			method: "POST",
			url: "http://localhost:8080/api/authentication/post/comment/insertcomment",
			data: comment
		})

        // if (response.data !== null) {
        //     if (response.data.status === 'success') {
        //     }
        // }
    }

    function handleReplyClick(index: any) {
        // showReplyInput[index] = !showReplyInput[index];
    }

    async function savePosts(postItem: Post[]) {
        collection.savedPosts = postItem;
        collection.memberId = memberId;

        const response = await axios({
			method: "POST",
			url: "http://localhost:8080/api/authentication/collections/add_post_to_collection",
			data: collection
		})
    }

    async function sharePosts(post: Post) {
        let circleId  = currentCircleId;
        let shareContent = shareContentText;
        const shareData = {post, owner, shareContent, circleId};

        const response = await axios({
            method: "POST",
			url: "http://localhost:8080/api/authentication/post/share",
			data: shareData
        })

        if (response.data.status == "success") {
            window.location.reload();
        }
    }

    function openShare() {
        share = !share;
    }

    function cancelShare() {
        share = false;
    }

    async function joinCircle(memberId: any, circleId: any) {
        const join = {memberId, circleId};
        const response = await axios({
            method: "POST",
			url: "http://localhost:8080/api/authentication/circle/join",
			data: join
        })

        if (response.data.status == "success") {
            window.location.reload();
        }
    }

    let activeTab = 'Posts';
    let showDropdown = false;
    let viewTypes = ['Cards', 'Classic', 'Compact'];
    let selectedViewType = 'Cards';

</script>

<div class="grid grid-cols-1 md:grid-cols-5 gap-5 mt-10">
    <!-- Circle Information -->
    <div class="col-span-1 md:col-span-1">
        <div class="mb-5 rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
            <div class="p-5 flex items-center justify-center">
                <div>
                    <img class="rounded-full w-20 h-20 object-cover" src="{data.details[0].avatar}" alt="{data.details[0].name}">
                </div>
            </div>
            <div class="flex flex-col space-y-2 px-4">
                <h1 class="text-lg font-bold text-black text-center inline-block">{data.details[0].name}</h1>
                <div class="flex flex-col space-y-2 px-5 md:flex-row md:space-y-0 space-x-2 items-center group">
                    <div>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 group-hover:text-indigo-400">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"></path>
                        </svg>  
                    </div>
                    <div class="pl-3 pb-3">
                        <p class="font-bold text-sm lg:text-base text-slate-400 leading-4 group-hover:text-indigo-400">Create at</p>
                        <p class="text-slate-400 text-sm hidden md:block">{FormatDate(data.details[0].createdAt)}</p>
                    </div>
                </div>
                <div class="flex flex-col space-y-2 px-5 md:flex-row md:space-y-0 space-x-2 items-center group">
                    <div>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 group-hover:text-indigo-400">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15 19.128a9.38 9.38 0 002.625.372 9.337 9.337 0 004.121-.952 4.125 4.125 0 00-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 018.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0111.964-3.07M12 6.375a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zm8.25 2.25a2.625 2.625 0 11-5.25 0 2.625 2.625 0 015.25 0z"></path>
                        </svg>   
                    </div>
                    <div class="pl-3 pb-3">
                        <p class="font-bold text-sm lg:text-base text-slate-400 leading-4 group-hover:text-indigo-400">Member</p>
                        <p class="text-slate-400 text-sm hidden md:block">{circleMembersCount.length}</p>
                    </div>
                </div>
                <div class="flex flex-col space-y-2 px-5 md:flex-row md:space-y-0 space-x-2 items-center group">
                    <div>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 group-hover:text-indigo-400">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.324.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 011.37.49l1.296 2.247a1.125 1.125 0 01-.26 1.431l-1.003.827c-.293.24-.438.613-.431.992a6.759 6.759 0 010 .255c-.007.378.138.75.43.99l1.005.828c.424.35.534.954.26 1.43l-1.298 2.247a1.125 1.125 0 01-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.57 6.57 0 01-.22.128c-.331.183-.581.495-.644.869l-.213 1.28c-.09.543-.56.941-1.11.941h-2.594c-.55 0-1.02-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 01-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 01-1.369-.49l-1.297-2.247a1.125 1.125 0 01.26-1.431l1.004-.827c.292-.24.437-.613.43-.992a6.932 6.932 0 010-.255c.007-.378-.138-.75-.43-.99l-1.004-.828a1.125 1.125 0 01-.26-1.43l1.297-2.247a1.125 1.125 0 011.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.087.22-.128.332-.183.582-.495.644-.869l.214-1.281z"></path>
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
                        </svg>  
                    </div>
                    <div class="pl-3 pb-3">
                        <p class="font-bold text-sm lg:text-base text-slate-400 leading-4 group-hover:text-indigo-400">Setting</p>
                        <p class="text-slate-400 text-sm hidden md:block"></p>
                    </div>
                </div>
                <div class="flex justify-center ">
                    <button class="mb-4 bg-blue-500 hover:bg-blue-700 text-white text-sm font-bold py-2 rounded w-20 justify-center">
                        {#if isJoined}
                            <span>Joined</span>
                        {:else}
                            <!-- svelte-ignore a11y-no-static-element-interactions -->
                            <!-- svelte-ignore a11y-click-events-have-key-events -->
                            <span on:click={() => joinCircle(memberId, currentCircleId)}>Join</span>
                        {/if}
                    </button>
                </div>
                
            </div>
        </div>
        <div class="rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
            <div class="p-5">
                <ul class="">
                    <li class="flex items-center justify-center py-2 ">
                        <a class="hover:text-gray-500" href="#">Rules</a>
                    </li>
                    <li class="flex items-center justify-center py-2 ">
                        <a class="hover:text-gray-500" href="#">Goods</a>
                    </li>
                    <li class="flex items-center justify-center py-2 ">
                        <a class="hover:text-gray-500" href="#">Services</a>
                    </li>
                    <li class="flex items-center justify-center py-2 ">
                        <a class="hover:text-gray-500" href="#">Event</a>
                    </li>
                    <li class="flex items-center justify-center py-2 ">
                        <a class="hover:text-gray-500" href="#">Threads</a>
                    </li>
                    <li class="flex items-center justify-center py-2 ">
                        <a class="hover:text-gray-500" href="#">Activity</a>
                    </li>
                </ul>
            </div>
            
        </div>
    </div>
    <!-- Post Card -->
    <!-- svelte-ignore a11y-no-static-element-interactions -->
    <div class="col-span-1 md:col-span-3">
        <!--Small post making form -->
        <!-- svelte-ignore a11y-click-events-have-key-events -->
        <div class="bg-white shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] p-2 rounded-xl dark:bg-gray-700 mb-4 z-1">
            <div class="flex items-center mb-1 relative">
                <a href="/account/">
                    <img src="{currentAvatar}" alt="Avatar" class="w-10 h-10 rounded-full mr-4 z-2" />
                    <span class="absolute bottom-0 left-7 w-3 h-3 rounded-full bg-green-500 border border-white"></span>
                </a>
                <div class="flex flex-grow space-x-2">
                    <input
                        type="text"
                        placeholder="What's on your mind?"
                        class="flex-grow bg-gray-50 border border-gray-300 rounded-md p-2"
                        on:click|preventDefault={submitForm}
                    />
                    <label class="flex items-center cursor-pointer">
                    <span class="p-2 rounded-md hover:bg-gray-200" on:click|preventDefault={submitForm}>
                        <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" viewBox="0 0 512 512" id="images">
                            <path d="M457.6 140.2l-82.5-4-4.8-53.8c-1-11.3-11.1-19.2-22.9-18.3l-296 24.3c-11.8 1-20.3 10.5-19.4 21.7l21.2 235.8c1 11.3 11.2 19.2 22.9 18.3l15-1.2-2.4 45.8c-.6 12.6 9.2 22.8 22.4 23.5L441.3 448c13.2.6 24.1-8.6 24.8-21.2L480 163.5c.6-12.5-9.3-22.7-22.4-23.3zm-355 5.3l-7.1 134.8L78.1 305l-16-178v-.5-.5c.5-5 4.3-9 9.5-9.4l261-21.4c5.2-.4 9.7 3 10.5 7.9 0 .2.3.2.3.4 0 .1.3.2.3.4l2.7 30.8-219-10.5c-13.2-.4-24.2 8.8-24.8 21.3zm334.1 236.9L390 327.1l-27.5-32.7c-2.4-2.9-6.3-5.3-10.6-5.5-4.3-.2-7.5 1.5-11.1 4.1l-16.4 11.9c-3.5 2.1-6.2 3.5-9.9 3.3-3.6-.2-6.8-1.6-9.1-3.8-.8-.8-2.3-2.2-3.5-3.4l-42.8-48.9c-3.1-3.9-8.2-6.4-13.8-6.7-5.7-.3-11.2 2.1-14.8 5.6L129.4 359.8l-6.8 7.4.3-6.8 6.8-128.9 3.3-62.9v-.5-.5c1.4-5.4 6.2-9.3 11.9-9l204.2 9.8 28.7 1.4 58.3 2.8c5.8.3 10.3 4.7 10.4 10.2 0 .2.3.3.3.5s.3.3.3.5l-10.4 198.6z"></path>
                            <path d="M373.2 262.3c19.4 0 35.2-15.8 35.2-35.2s-15.7-35.2-35.2-35.2c-19.4 0-35.2 15.7-35.2 35.2s15.7 35.2 35.2 35.2z"></path>
                        </svg>
                    <input type="file" bind:files={picture} class="hidden" />
                    </label>
                    <label class="flex items-center cursor-pointer">
                    <span class="p-2 rounded-md hover:bg-gray-200" on:click|preventDefault={submitForm}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M18.375 12.739l-7.693 7.693a4.5 4.5 0 01-6.364-6.364l10.94-10.94A3 3 0 1119.5 7.372L8.552 18.32m.009-.01l-.01.01m5.699-9.941l-7.81 7.81a1.5 1.5 0 002.112 2.13" />
                        </svg>                                           
                    </span>
                    <input type="file" bind:files={file} class="hidden" />
                    </label>
                </div>
            </div>
        </div>
        <!-- Tabs buttons -->
        <div class="flex mb-4 border-b border-gray-200">
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
        {#if activeTab === 'Posts'}
            <!-- Theme box -->
            <div class="t-box border p-3 rounded-2xl mb-4 shadow flex justify-between items-center" style="background-image: url('https://i.ytimg.com/vi/Cvqp1ZsdjWE/maxresdefault.jpg');">
                <p class="capitalized pl-2 inline-block text-base font-bold text-white align-middle">Posts related to {currentCircle}</p>

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
                {#each data.posts as postItem}
                    <!-- Shared posts -->
                    {#if postItem.from}
                        <div class="border p-6 rounded-2xl mb-4">
                            <div class="flex items-center justify-between">
                                <div class="flex gap-3.5 items-center">
                                    <img src="{postItem.member.avatar}" class="object-cover rounded-full w-10 h-10" alt="post"/>
                                    <div class="flex flex-col">
                                        <b class="mb-2">{postItem.member.name}</b>
                                        <p class="text-gray-400 text-xs">{FormatDateTime(postItem.modifiedAt)}</p>
                                    </div>
                                </div>
                            </div>
                            <p class="whitespace-pre-wrap mt-5 text-lg font-semibold mb-3">{postItem.shareContent}</p>
                            <div class="border rounded-2xl p-6">
                                <!-- Header Post -->
                                <div class="flex items-center justify-between">
                                    <!-- Avatar -->
                                    <div class="gap-3.5	flex items-center ">
                                        <img src="{postItem.from.member.avatar}" class="object-cover rounded-full w-10 h-10" alt="post"/>
                                        <div class="flex flex-col">
                                            <b class="mb-2">{postItem.from.member.name}</b>
                                            <p class="text-gray-400 text-xs">{FormatDateTime(postItem.from.createdAt)}</p>
                                        </div>
                                    </div>
                                    <!-- Loading SVG  -->
                                    <div class="bg-gray-100	rounded-full h-3.5 flex	items-center justify-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="34px" fill="#92929D">
                                            <path d="M0 0h24v24H0V0z" fill="none" />
                                            <path
                                                d="M6 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm12 0c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm-6 0c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z" />
                                        </svg>
                                    </div>
                                </div>
                                <!-- Title From Post -->
                                <div class="whitespace-pre-wrap mt-7 font-semibold text-xl">
                                    <p>{postItem.from.title}</p>
                                </div>
                                <!-- Content From Post -->
                                <div class="whitespace-pre-wrap mt-2">
                                    <p>{@html postItem.from.content}</p>
                                </div>
                                
                                <!-- Image/Video From Post-->
                                {#if postItem.from.image !== null}
                                    <div class="flex justify-center flex-wrap">
                                        <img src="{postItem.from.image}" class="rounded-2xl w-full object-cover h-96 flex-auto" alt="post">
                                    </div>
                                {/if}  
                            </div>
                            
                            <!-- Reaction -->
                            <div class=" h-16 border-b  flex items-center justify-around">
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
                                    <div class="text-sm	">{comments.length} Comments</div>
                                </div>
                                <div class="flex items-center gap-3">
                                    <!-- svelte-ignore a11y-click-events-have-key-events -->
                                    <svg on:click={handleLikeClick} xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-red-500 hover:cursor-pointer hover:animate-bounce" viewBox="0 0 20 20"
                                        fill="currentColor">
                                        <path fill-rule="evenodd"
                                            d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
                                            clip-rule="evenodd" />
                                    </svg>
                                    <div class="text-sm">{likesCount} Likes</div>
                                </div>
                                <!-- svelte-ignore a11y-click-events-have-key-events -->
                                <div class="flex items-center gap-3" on:click={openShare}>
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
                                    <div class="text-sm">Share</div>
                                </div>

                                <!-- svelte-ignore a11y-click-events-have-key-events -->
                                <!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
                                <div class="flex items-center gap-3" on:click={() => savePosts(postItem)}>
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
                            <!-- Enter a comment -->
                            <div class="flex items-center justify-between mt-4">
                                <img src="{currentAvatar}" class="rounded-full w-10 h-10 object-cover border" alt="pic">
                                <div class="flex items-center justify-between bg-gray-50 h-11 w-11/12 border rounded-2xl overflow-hidden px-4 mx-2">
                                    <input type="text" bind:value={commentText} class="h-full w-full bg-gray-50 outline-none" placeholder="Write your comment..." name="comment">
                                </div>
                                <button on:click={handleCommentClick}>
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 hover:text-blue-500 hover:animate-bounce">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5" />
                                    </svg>
                                </button>
                            </div>
                            <!-- Comments go here -->
                            {#each comments as comment, index}
                                <div class="flex items-start mt-4 p-4 bg-gray-100 rounded-lg">
                                    <img src="{comment.memAvt}" class="rounded-full w-10 h-10 object-cover border" alt="pic">
                                    <div class="ml-4 flex-1">
                                        <p class="font-semibold text-base">{comment.memName}</p>
                                        <p class="text-gray-600 text-sm">{comment.content}</p>
                                        <div class="flex items-center mt-2 text-sm">
                                            <button class="text-gray-600 hover:text-blue-600 focus:outline-none mr-4">Like</button>
                                            <button on:click={() => handleReplyClick(index)} class="text-gray-600 hover:text-blue-600 focus:outline-none mr-4">Reply</button>
                                            <button class="text-gray-600 hover:text-blue-600 focus:outline-none">Report</button>
                                        </div>

                                        {#if showReplyInput[index]}
                                            <div class="mt-2 flex items-center justify-between bg-gray-50 h-full border rounded-full overflow-hidden px-4 ml-auto w-full">
                                                <input type="text" class="h-8 w-full bg-gray-50 outline-none text-sm py-1" placeholder="Write your thoughts..." name="reply">
                                            </div>
                                        {/if}
                                    </div>
                                </div>
                            {/each}   
                        </div>

                        <!-- Posts -->
                        {:else}
                        <div class="border rounded-2xl p-6 mb-4">
                            <!-- Header Post -->
                            <div class="flex items-center justify-between">
                                <!-- Avatar -->
                                <div class="gap-3.5	flex items-center ">
                                    <img src="{postItem.member.avatar}" class="object-cover rounded-full w-10 h-10" alt="post"/>
                                    <div class="flex flex-col">
                                        <b class="mb-2">{postItem.member.name}</b>
                                        <p class="text-gray-400 text-xs">{FormatDateTime(postItem.createdAt)}</p>
                                    </div>
                                </div>
                                <!-- Loading SVG  -->
                                <div class="bg-gray-100	rounded-full h-3.5 flex	items-center justify-center">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="34px" fill="#92929D">
                                        <path d="M0 0h24v24H0V0z" fill="none" />
                                        <path
                                            d="M6 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm12 0c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm-6 0c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z" />
                                    </svg>
                                </div>
                            </div>
                            <!-- Title Post -->
                            <div class="whitespace-pre-wrap mt-7 font-semibold text-xl">
                                <p>{postItem.title}</p>
                            </div>
                            <!-- Content Post -->
                            <div class="whitespace-pre-wrap mt-2">
                                <p>{@html postItem.content}</p>
                            </div>
                            
                            <!-- Image/Video Post-->
                            {#if postItem.image !== null}
                                <div class="flex justify-center border-b pb-4 flex-wrap">
                                    <img src="{postItem.image}" class="rounded-2xl w-full object-cover h-96 flex-auto" alt="post">
                                </div>
                            {/if}
            
                            <!-- Reaction -->
                            <div class=" h-16 border-b  flex items-center justify-around">
                                <div class="flex items-center	gap-3	">
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
                                    <div class="text-sm	">{comments.length} Comments</div>
                                </div>
                                <div class="flex items-center gap-3">
                                    <!-- svelte-ignore a11y-click-events-have-key-events -->
                                    <svg on:click={handleLikeClick} xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-red-500 hover:cursor-pointer hover:animate-bounce" viewBox="0 0 20 20"
                                        fill="currentColor">
                                        <path fill-rule="evenodd"
                                            d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
                                            clip-rule="evenodd" />
                                    </svg>
                                    <div class="text-sm">{likesCount} Likes</div>
                                </div>
                                <!-- svelte-ignore a11y-click-events-have-key-events -->
                                <div class="flex items-center gap-3" on:click={openShare}>
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
                                    <div class="text-sm">Share</div>
                                </div>

                                <!-- Share dialog -->
                                {#if share}
                                    <div class="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 z-30 flex items-center justify-center" transition:fade={{duration: 100}}>
                                        <div class="bg-white p-4 rounded-md shadow-lg w-full max-w-xl mx-auto my-20 max-h-96 overflow-y-auto " aria-labelledby="modal-title" role="dialog" aria-modal="true">
                                                <div class="w-full border-b-2 border-blue-500 flex mb-2 text-2xl">
                                                    <h1 class="text-lg font-semibold mb-2 justify-between">Share Post</h1>
                                                    <!-- <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 r-0">
                                                        <path fill-rule="evenodd" d="M5.47 5.47a.75.75 0 011.06 0L12 10.94l5.47-5.47a.75.75 0 111.06 1.06L13.06 12l5.47 5.47a.75.75 0 11-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 01-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 010-1.06z" clip-rule="evenodd" />
                                                    </svg> -->
                                                </div>

                                                <div class="w-full p-2 mb-4 rounded-md outline-none relative" aria-label="What's on your mind?" contenteditable="true" role="textbox" tabindex="0" spellcheck="false" 
                                                        on:keydown="{e => shareContentText = (e.target instanceof HTMLElement) ? e.target.textContent : ''}"
                                                        on:keyup="{e => { if(e.target instanceof HTMLElement && e.target.textContent.trim() === '') shareContentText = ''; }}">
                                                    {#if !shareContentText}
                                                        <div class="absolute inset-0 flex items-center">
                                                            <div class="p-2">
                                                                <p class="text-gray-400">What's on your mind?</p>
                                                            </div>
                                                        </div>
                                                    {/if}
                                                </div>
                                            
                                                <div class="border rounded-2xl p-4 mb-4">
                                                    <!-- Header Post -->
                                                    <div class="flex items-center justify-between">
                                                        <!-- Avatar -->
                                                        <div class="gap-3.5	flex items-center ">
                                                            <img src="{postItem.member.avatar}" class="object-cover rounded-full w-6 h-6" alt="post"/>
                                                            <div class="flex flex-col">
                                                                <b class="mb-2">{postItem.member.name}</b>
                                                                <p class="text-gray-400 text-xs">{FormatDateTime(postItem.createdAt)}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Title Post -->
                                                    <div class="whitespace-pre-wrap mt-7 font-semibold text-sm">
                                                        <p>{postItem.title}</p>
                                                    </div>
                                                    <!-- Content Post -->
                                                    <div class="whitespace-pre-wrap mt-2">
                                                        <p>{@html postItem.content}</p>
                                                    </div>
                                                    
                                                    <!-- Image/Video Post-->
                                                    {#if postItem.image !== null}
                                                        <div class="flex justify-center flex-wrap">
                                                            <img src="{postItem.image}" class="rounded-2xl w-full object-cover h-96 flex-auto" alt="post">
                                                        </div>
                                                    {/if}
                                                </div>

                                                <div class="flex justify-end space-x-2 mt-4">
                                                    <button class="bg-red-600 r-0 hover:bg-red-500 text-white font-bold py-1.5 px-3 border-b-4 border-red-700 hover:shadow-sm transition-all duration-300 rounded" 
                                                        on:click={cancelShare}>
                                                        <span>Cancel</span>
                                                    </button>
                                                    <button class="bg-blue-600 disabled:bg-gray-300 disabled:border-gray-500 r-0 hover:bg-blue-500 text-white font-bold py-1.5 px-3 border-b-4 border-blue-700 hover:shadow-sm transition-all duration-300 rounded" 
                                                        on:click={() => sharePosts(postItem)}>
                                                        <span>Share</span>
                                                    </button>
                                                </div>
                                                
                                        </div>
                                    </div>
                                
                                {/if}
                                
                                <!-- svelte-ignore a11y-click-events-have-key-events -->
                                <!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
                                <div class="flex items-center gap-3" on:click={() => savePosts(postItem)}>
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
                            <!-- Enter a comment -->
                            <div class="flex items-center justify-between mt-4">
                                <img src="{currentAvatar}" class="rounded-full w-10 h-10 object-cover border" alt="pic">
                                <div class="flex items-center justify-between bg-gray-50 h-11 w-11/12 border rounded-2xl overflow-hidden px-4 mx-2">
                                    <input type="text" bind:value={commentText} class="h-full w-full bg-gray-50 outline-none" placeholder="Write your comment..." name="comment">
                                </div>
                                <button on:click={handleCommentClick}>
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 hover:text-blue-500 hover:animate-bounce">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5" />
                                    </svg>
                                </button>
                            </div>
                            <!-- Comments go here -->
                            {#each comments as comment, index}
                                <div class="flex items-start mt-4 p-4 bg-gray-100 rounded-lg">
                                    <img src="{comment.memAvt}" class="rounded-full w-10 h-10 object-cover border" alt="pic">
                                    <div class="ml-4 flex-1">
                                        <p class="font-semibold text-base">{comment.memName}</p>
                                        <p class="text-gray-600 text-sm">{comment.content}</p>
                                        <div class="flex items-center mt-2 text-sm">
                                            <button class="text-gray-600 hover:text-blue-600 focus:outline-none mr-4">Like</button>
                                            <button on:click={() => handleReplyClick(index)} class="text-gray-600 hover:text-blue-600 focus:outline-none mr-4">Reply</button>
                                            <button class="text-gray-600 hover:text-blue-600 focus:outline-none">Report</button>
                                        </div>
            
                                        {#if showReplyInput[index]}
                                            <div class="mt-2 flex items-center justify-between bg-gray-50 h-full border rounded-full overflow-hidden px-4 ml-auto w-full">
                                                <input type="text" class="h-8 w-full bg-gray-50 outline-none text-sm py-1" placeholder="Write your thoughts..." name="reply">
                                            </div>
                                        {/if}
                                    </div>
                                </div>
                            {/each}     
                        </div>
                    {/if}
                    
                {/each}
            
                <!-- Classic layout-->
                {:else if selectedViewType === 'Classic'}
                    
                <!-- Compact layout-->    
                {:else if selectedViewType === 'Compact'}
            {/if}
        
        {:else if activeTab === "Communities"}
            <!-- Theme box -->
            <div class="t-box border p-3 rounded-2xl mb-4 shadow flex justify-between items-center" style="background-image: url('https://i.ytimg.com/vi/Cvqp1ZsdjWE/maxresdefault.jpg');">
                <p class="capitalized pl-2 inline-block text-base font-bold text-white align-middle">Communities related to {currentCircle}</p>

                <div class="inline-block left-0">
                    <div class="mt-2 w-6 h-6"></div>                    
                </div>
            </div>
            <div class="border p-6 rounded-2xl mb-4">
                {#each data.details[0].children as subCirItems}
                    <div class="flex items-center justify-between mb-6">
                        <!-- Circle avatar -->
                        <div class="gap-3.5 flex">
                            <img src="{subCirItems.avatar}" class="object-cover rounded-full w-14 h-14 flex-shrink-0" alt="circle"/>
                            <div class="flex flex-col -mt-1">
                                <!-- on:click={selectCircle(postItem.p_circleId, postItem.p_circleName)} -->
                                <p class="capitalize text-base font-semibold"><a href="/subCircles/{subCirItems.id}" class="hover:underline hover:text-blue-400">{subCirItems.name}</a></p>
                                <!-- <p class="text-gray-600 text-xs pb-1 font-medium">{subCirItems.membersCounts.length} member</p> -->
                                <p class="text-gray-600 text-xs pb-1 font-medium">1 member</p>

                                <!-- <p class="text-gray-500 text-xs mr-6 pb-2">{subCirItems.des}{subCirItems.des}</p> -->
                                <a href="/circles/{subCirItems.id}" class="w-fit text-sm font-semibold text-blue-600 hover:underline">View</a>
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
        <!-- Create sub circle button  -->
        <button on:click={createCircle} class="relative mb-4 w-full rounded-xl px-6 py-2.5 overflow-hidden group bg-blue-500 relative hover:bg-gradient-to-r hover:from-blue-500 hover:to-blue-400 text-white hover:ring-2 hover:ring-offset-2 hover:ring-blue-400 transition-all ease-out duration-300">
            <span class="absolute right-0 w-8 h-32 -mt-12 transition-all duration-1000 transform translate-x-12 bg-white opacity-10 rotate-12 group-hover:-translate-x-40 ease"></span>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 absolute left-full transition-all duration-500 group-hover:left-48 group-hover:rotate-180 transform transition duration-300">
                <path d="M15.75 8.25a.75.75 0 01.75.75c0 1.12-.492 2.126-1.27 2.812a.75.75 0 11-.992-1.124A2.243 2.243 0 0015 9a.75.75 0 01.75-.75z" />
                <path fill-rule="evenodd" d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zM4.575 15.6a8.25 8.25 0 009.348 4.425 1.966 1.966 0 00-1.84-1.275.983.983 0 01-.97-.822l-.073-.437c-.094-.565.25-1.11.8-1.267l.99-.282c.427-.123.783-.418.982-.816l.036-.073a1.453 1.453 0 012.328-.377L16.5 15h.628a2.25 2.25 0 011.983 1.186 8.25 8.25 0 00-6.345-12.4c.044.262.18.503.389.676l1.068.89c.442.369.535 1.01.216 1.49l-.51.766a2.25 2.25 0 01-1.161.886l-.143.048a1.107 1.107 0 00-.57 1.664c.369.555.169 1.307-.427 1.605L9 13.125l.423 1.059a.956.956 0 01-1.652.928l-.679-.906a1.125 1.125 0 00-1.906.172L4.575 15.6z" clip-rule="evenodd" />
            </svg>  
            <span class="relative font-bold right-2">Create new Sub Circle</span>
        </button>
        
        <!-- Related community -->
        <div class="rounded-xl mb-4 shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
            <div class="bg-white/10 col-span-3 rounded-lg p-4">
                <h1 class="pb-3">Related community</h1>
                <hr class="py-3 border-slate-200">
                <a href="#" class="flex flex-col space-y-2 md:space-y-0 md:flex-row mb-5 items-center md:space-x-2 hover:bg-white/10 group transition duration-150 ease-linear rounded-lg group w-full">
                    <div>
                        <img class="rounded-full w-8 h-8 object-cover" src="https://img.freepik.com/free-photo/no-problem-concept-bearded-man-makes-okay-gesture-has-everything-control-all-fine-gesture-wears-spectacles-jumper-poses-against-pink-wall-says-i-got-this-guarantees-something_273609-42817.jpg?w=1800&amp;t=st=1669749937~exp=1669750537~hmac=4c5ab249387d44d91df18065e1e33956daab805bee4638c7fdbf83c73d62f125" alt="">
                    </div>
                    <div>
                        <p class="font-nomal text-sm hover:text-indigo-400 leading-4">Group Name</p>
                        <span class="text-xs text-slate-400">100</span>
                    </div>
                </a>
            </div>
        </div>

        <!-- Recent circles -->
        <div class="rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
            <div class="bg-white/10 col-span-3 rounded-lg p-4">
                <h1 class="pb-3">Recent Circles</h1>
                <hr class="py-3 border-slate-200">
                {#each data.details[0].children as subCirItems}
                    <a href="/circles/subCircle/{subCirItems.id}" class="flex flex-col space-y-2 md:space-y-0 md:flex-row mb-5 items-center md:space-x-2 hover:bg-white/10 group transition duration-150 ease-linear rounded-lg group w-full">
                        <div>
                            <img class="rounded-full w-8 h-8 object-cover" src="{subCirItems.avatar}" alt="{subCirItems.name}">
                        </div>
                        <div>
                            <p class="font-nomal text-sm hover:text-indigo-400 leading-4">{subCirItems.name}</p>
                            <span class="text-xs text-slate-400">1</span>
                        </div>
                    </a>
                {/each}
            </div>
        </div>
    </div>

    {#if createSubCir}
        <div class="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 z-30" transition:fade={{duration: 100}}>
            <div class="bg-white p-4 rounded-md shadow-lg w-full max-w-md mx-auto my-20">
                <div class="w-full border-b-2 border-blue-500 flex mb-3 text-2xl">
                    <h1 class="text-lg font-semibold mb-2 justify-between">Create Sub Circle in {currentCircle}</h1>
                    <!-- <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 r-0">
                        <path fill-rule="evenodd" d="M5.47 5.47a.75.75 0 011.06 0L12 10.94l5.47-5.47a.75.75 0 111.06 1.06L13.06 12l5.47 5.47a.75.75 0 11-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 01-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 010-1.06z" clip-rule="evenodd" />
                    </svg> -->
                </div>

                {#if step == 0} <!-- title & description -->
                    <label for="group-title" class="block text-sm font-medium text-gray-700">Title</label>
                    <input bind:value={circle.name} type="text" placeholder="Please enter title" id="group-title" class="w-full mt-2 rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" />
                    <label for="group-description" class="block text-sm font-medium text-gray-700 mt-2">Description</label>
                    <textarea bind:value={circle.description} 
                        rows="5" placeholder="Please enter description" id="group-description" 
                        class="w-full max-h-48 mt-2 rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base"></textarea>	

                {:else if step == 1} <!-- banner & avatar -->
                    <div class="flex flex-col justify-center items-center">
                        <label
                        class="w-full h-48 cursor-pointer rounded-lg border-2 border-dashed border-base-200 overflow-hidden flex items-center justify-center bg-cover"
                        style="background-image: url({circle.banner});">
                        <svg class="h-16 -mt-8 text-base-200 {circle.banner ? 'hidden' : 'block'}" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <linearGradient id="_2" gradientUnits="userSpaceOnUse" x1="2.06" x2="21.5" y1="17.499" y2="17.499">
                                <stop offset="0" stop-color="#16b0e2"/>
                                <stop offset="1" stop-color="#6e5af0"/>
                            </linearGradient>
                            <linearGradient id="_2-2" x1="1.5" xlink:href="#_2" y1="11.015" y2="11.015"/>
                            <linearGradient id="_2-3" x1="6.125" x2="10.875" xlink:href="#_2" y1="8.5" y2="8.5"/>
                            <linearGradient id="_2-4" x1="15.5" x2="22.5" xlink:href="#_2" y1="5" y2="5"/>
                            <g id="_01" data-name="01">
                                <path d="m21.5 14.4v2.29c0 3.64-2.17 5.81-5.81 5.81h-8.38c-2.55 0-4.39-1.07-5.25-2.97l.11-.08 4.92-3.3a2.221 2.221 0 0 1 2.64.14l.34.28a2.229 2.229 0 0 0 2.82 0l4.16-3.57a2.229 2.229 0 0 1 2.82 0z" fill="url(#_2)"/>
                                <path d="m21.5 8.277v.033 6.09l-1.63-1.4a2.229 2.229 0 0 0 -2.82 0l-4.16 3.57a2.229 2.229 0 0 1 -2.82 0l-.34-.28a2.221 2.221 0 0 0 -2.64-.14l-4.92 3.3-.11.08a6.723 6.723 0 0 1 -.56-2.84v-8.38c0-3.64 2.17-5.81 5.81-5.81h8.38.033a2.314 2.314 0 0 0 -.223 1.03v2.94a1.866 1.866 0 0 0 2.03 2.03h2.94a2.314 2.314 0 0 0 1.03-.223z" fill="url(#_2-2)" opacity=".4"/>
                                <path d="m10.875 8.5a2.375 2.375 0 1 1 -2.375-2.375 2.377 2.377 0 0 1 2.375 2.375z" fill="url(#_2-3)"/><path d="m20.47 1.5h-2.94a1.866 1.866 0 0 0 -2.03 2.03v2.94a1.866 1.866 0 0 0 2.03 2.03h2.94a1.866 1.866 0 0 0 2.03-2.03v-2.94a1.866 1.866 0 0 0 -2.03-2.03zm.94 3.93a.647.647 0 0 1 -.41.18h-1.41l.01 1.39a.632.632 0 0 1 -.19.43.59.59 0 0 1 -.41.17.6.6 0 0 1 -.6-.6v-1.4l-1.4.01a.61.61 0 0 1 -.6-.61.6.6 0 0 1 .6-.6l1.4.01v-1.4a.6.6 0 1 1 1.2 0l-.01 1.39h1.41a.6.6 0 0 1 .6.6.681.681 0 0 1 -.19.43z" fill="url(#_2-4)"/>
                            </g>
                        </svg>
                        <input type="file" class="hidden" accept=".jpg, .jpeg, .png" on:change={bannerSelected}/>
                        </label>

                        <label
                        class="-mt-12 w-24 h-24 z-40 cursor-pointer bg-base-100 flex justify-center items-center rounded-full border-2 border-dashed border-base-200 bg-cover"
                        style="background-image: url({circle.avatar});">
                    
                        <svg class="w-8 h-8 text-base-200 z-30 {circle.avatar ? 'hidden' : 'block'}" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" >
                            <linearGradient id="_2" gradientUnits="userSpaceOnUse" x1="2.06" x2="21.5" y1="17.499" y2="17.499">
                                <stop offset="0" stop-color="#16b0e2"/>
                                <stop offset="1" stop-color="#6e5af0"/>
                            </linearGradient>
                            <linearGradient id="_2-2" x1="1.5" xlink:href="#_2" y1="11.015" y2="11.015"/>
                            <linearGradient id="_2-3" x1="6.125" x2="10.875" xlink:href="#_2" y1="8.5" y2="8.5"/>
                            <linearGradient id="_2-4" x1="15.5" x2="22.5" xlink:href="#_2" y1="5" y2="5"/>
                            <g id="_01" data-name="01">
                                <path d="m21.5 14.4v2.29c0 3.64-2.17 5.81-5.81 5.81h-8.38c-2.55 0-4.39-1.07-5.25-2.97l.11-.08 4.92-3.3a2.221 2.221 0 0 1 2.64.14l.34.28a2.229 2.229 0 0 0 2.82 0l4.16-3.57a2.229 2.229 0 0 1 2.82 0z" fill="url(#_2)"/>
                                <path d="m21.5 8.277v.033 6.09l-1.63-1.4a2.229 2.229 0 0 0 -2.82 0l-4.16 3.57a2.229 2.229 0 0 1 -2.82 0l-.34-.28a2.221 2.221 0 0 0 -2.64-.14l-4.92 3.3-.11.08a6.723 6.723 0 0 1 -.56-2.84v-8.38c0-3.64 2.17-5.81 5.81-5.81h8.38.033a2.314 2.314 0 0 0 -.223 1.03v2.94a1.866 1.866 0 0 0 2.03 2.03h2.94a2.314 2.314 0 0 0 1.03-.223z" fill="url(#_2-2)" opacity=".4"/>
                                <path d="m10.875 8.5a2.375 2.375 0 1 1 -2.375-2.375 2.377 2.377 0 0 1 2.375 2.375z" fill="url(#_2-3)"/><path d="m20.47 1.5h-2.94a1.866 1.866 0 0 0 -2.03 2.03v2.94a1.866 1.866 0 0 0 2.03 2.03h2.94a1.866 1.866 0 0 0 2.03-2.03v-2.94a1.866 1.866 0 0 0 -2.03-2.03zm.94 3.93a.647.647 0 0 1 -.41.18h-1.41l.01 1.39a.632.632 0 0 1 -.19.43.59.59 0 0 1 -.41.17.6.6 0 0 1 -.6-.6v-1.4l-1.4.01a.61.61 0 0 1 -.6-.61.6.6 0 0 1 .6-.6l1.4.01v-1.4a.6.6 0 1 1 1.2 0l-.01 1.39h1.41a.6.6 0 0 1 .6.6.681.681 0 0 1 -.19.43z" fill="url(#_2-4)"/>
                            </g>
                        </svg>
                        <input type="file" class="hidden z-40" accept=".jpg, .jpeg, .png, .svg" on:change={avatarSelected} />
                        </label>
                        <div class="mt-2 text-xl font-medium">
                            <p class="text-center">{circle.name}</p>
                        </div>
                        <div class="mt-2 flex justify-center">
                            <p>{circle.description}</p>
                        </div>
                    </div>

                <!-- {:else if step == 3} -->	
                        <!-- Add tags -->
                <!-- {:else if step == 4} -->
        
                {/if}
        
                <div class="text-right mt-4">
                    <button on:click={previous} class="bg-red-600 r-0 hover:bg-red-500 text-white font-bold py-1.5 px-3 border-b-4 border-red-700 hover:shadow-sm transition-all duration-300 rounded">
                        {step == 0 ? "Cancel" : "Previous"}
                    </button>
                    <button on:click={next} disabled={!canGoNext} class="bg-blue-600 disabled:bg-gray-300 disabled:border-gray-500 r-0 hover:bg-blue-500 text-white font-bold py-1.5 px-3 border-b-4 border-blue-700 hover:shadow-sm transition-all duration-300 rounded">
                        {step == 1 ? "Create" : "Next"}
                    </button>
                </div>
            </div>
        
        </div>
        
    {/if}
</div>

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