import axios from 'axios';


export async function load({params}) {  
    const cirId = params.circles.split('/').pop();
    
    const detApi = 'http://localhost:8080/api/authentication/circle/' + cirId;
    const postApi = 'http://localhost:8080/api/authentication/post/' + cirId;

    const [detailsResponse, postsResponse] = await Promise.all([
        axios.get(detApi),
        axios.get(postApi),
    ]);

    const details = detailsResponse.data;
    let posts = postsResponse.data;

    //@ts-ignore
    posts = posts.sort((a: any, b: any) => new Date(b.createdAt) - new Date(a.createdAt));


    return {details, posts};
}
