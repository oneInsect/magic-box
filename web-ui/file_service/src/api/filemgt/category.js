import request from '@/utils/request'

export default {
    getCates(current, limit, cateQuery){
        return request({
            url: `/filemgt/categories/page/${current}/${limit}`,
            method: 'post',
            data: cateQuery
        })
    },
    addCate(category){
        return request({
            url: `/filemgt/categories/category`,
            method: 'post',
            data: category
        })
    },
    getCatesAll(){
        return request({
            url: `/filemgt/categories/all`,
            method: 'get'
        })
    }

}