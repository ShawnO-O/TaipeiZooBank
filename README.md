# TaipeiZooBank
此專案使用了 google AAC 的mvvm架構搭配Rx、LiveData、Retrofit，也使用了BootomSheetBehavior來顯示植物內容、CollapsingToolbarLayout可以在地區內容業瀏覽植物列表時隨著列表的滾動而縮上去，對使用者體驗有幫助。

使用了navigation，相對於目前市場主流的Tab式UI，比較陌生，但透過這次實作，能體會到他的強大之處。

實作的時候有發現openData的圖片網址都是http，這是google不允許的，雖然有找到解法，使用handleSSLHandshake信任全網站憑證，可是這對app是不好的

另外有把adapter裡的viewHolder獨立出來，可以讓Adpater看起來比較乾淨，而且可以達到reuse的作用

也使用FB的ShimmerFrameLayout，來取代一般的ProcessBar Loading
