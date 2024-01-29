    package com.jy.boardback.dto.Object;

    import java.util.ArrayList;
    import java.util.List;

    import com.jy.boardback.repository.resultSet.GetFavoriteListResultSet;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class FavoriteListItem {

        private String email;
        private String nickname;
        private String profileImage;


        
        /**
         * GetFavoriteListResultSet 인터페이스의 메서드들을 호출 ->
         * GetFavoriteListResultSet을 '구현'하는 생성자
         */
        public FavoriteListItem(GetFavoriteListResultSet resultSet){
            // System.out.println("2222222======================>" );
            
            this.email = resultSet.getEmail();
            this.nickname = resultSet.getNickname();
            this.profileImage = resultSet.getProfileImage();
        }

        /**
         * 리스트를 순회하여 여러 객체를 만드는 copyList 메소드
         * FavoriteListItem의 생성자를 활용하여 resultSets에 담겨있는 리스트들을 
         * FavoriteListItem 객체로 변환합니다. 
         * 이를 통해 새로운 FavoriteListItem 객체들의 리스트를 생성하고 반환합니다.
         *  */ 
        public static List<FavoriteListItem> copyList(List<GetFavoriteListResultSet> resultSets){
            
            //list : FavoriteListItem 객체들의 리스트
            List<FavoriteListItem>list = new ArrayList<>();

            
            for(GetFavoriteListResultSet resultSet: resultSets){

                // System.out.println("1111111=======================>" );
                FavoriteListItem favoriteListItem = new FavoriteListItem(resultSet);
                
                list.add(favoriteListItem);
            }
            return list;
        }
    }
