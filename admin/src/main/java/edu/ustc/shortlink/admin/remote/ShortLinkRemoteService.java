package edu.ustc.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.common.convention.result.Results;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkGroupCntQueryRespDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ljx
 * @Date: 2024/2/27 22:05
 */
public interface ShortLinkRemoteService {
     default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
         Map<String,Object> requestMap = new HashMap<>();
         requestMap.put("gid",requestParam.getGid());
         requestMap.put("current",requestParam.getCurrent());
         requestMap.put("size",requestParam.getSize());
         String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page", requestMap);
         return JSON.parseObject(resultPageStr, new TypeReference<>() {
         });
     }

     default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam) {
         String resultShortLinkCreateRespDTOStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create", JSON.toJSONString(requestParam));
         return JSON.parseObject(resultShortLinkCreateRespDTOStr, new TypeReference<>() {
         });
     }

     default Result<List<ShortLinkGroupCntQueryRespDTO>> listGroupShortLinkCount(List<String> groupIds) {
         Map<String,Object> requestMap = new HashMap<>();
         requestMap.put("groupIds",groupIds);
         String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count", requestMap);
         return JSON.parseObject(resultPageStr, new TypeReference<>() {
         });
     }

     default void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
         HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/update",JSON.toJSONString(requestParam));
     }

     default Result<String> getTitleByUrl(String url) {
         String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/title?url=" + url);
         return JSON.parseObject(resultPageStr, new TypeReference<>() {});
     }
}
