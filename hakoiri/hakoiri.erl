%%
%% Copyright 2012 agwlvssainokuni
%%
%% Licensed under the Apache License, Version 2.0 (the "License");
%% you may not use this file except in compliance with the License.
%% You may obtain a copy of the License at
%%
%%     http://www.apache.org/licenses/LICENSE-2.0
%%
%% Unless required by applicable law or agreed to in writing, software
%% distributed under the License is distributed on an "AS IS" BASIS,
%% WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
%% See the License for the specific language governing permissions and
%% limitations under the License.
%%

-module(hakoiri).
-compile(export_all).


solve() ->
	Ans = solve(
		1,
		[[first_board()]],
		gb_sets:new()
	),
	lists:foreach(
		fun(L) ->
			print_path(1, L)
		end,
		Ans
	).


solve(N, Paths, History0) ->

	io:format("STEP ~p...", [N]),
	History1 = lists:foldl(
		fun([H|_], Dict) ->
			gb_sets:add_element(encode_board_by_type(H), Dict)
		end,
		History0,
		Paths
	),
	NextPaths0 = next_paths(Paths, History1),

	{Temp, _} = lists:mapfoldl(
		fun([H|T], Set) ->
			E = encode_board_by_type(H),
			case gb_sets:is_element(E, Set) of
				true ->
					{duplicate, Set};
				false ->
					{[H|T], gb_sets:add_element(E, Set)}
			end
		end,
		gb_sets:new(),
		NextPaths0
	),

	NextPaths = lists:filter(
		fun(duplicate) -> false; (_) -> true end,
		Temp
	),

	io:format(" from ~p paths to ~p paths~n",
		[length(Paths), length(NextPaths)]),

	case
		[
			[H|T]
			||
			[H|T] <- NextPaths,
			(catch dict:fetch({1, 3}, H)) =:= musume
		]
	of
		[] ->
			solve(N+1, NextPaths, History1);
		Goals ->
			lists:map(
				fun lists:reverse/1,
				Goals
			)
	end.
			

first_board() ->
	dict:from_list([
		{{1, 0}, musume},
		{{0, 0}, chichi},
		{{3, 0}, haha},
		{{0, 2}, sofu},
		{{3, 2}, sobo},
		{{1, 2}, kyodai},
		{{1, 3}, kado},
		{{2, 3}, sado},
		{{0, 4}, wasai},
		{{3, 4}, shodo}
	]).


next_paths(Paths, History) ->
	lists:append(
		lists:map(
			fun([H|T]) ->
				[
					[B,H|T]
					||
					B <- next_boards(H),
					gb_sets:is_element(encode_board_by_type(B), History) =:= false
				]
			end,
			Paths
		)
	).


next_boards(Board) ->
	RevBoard = dict:from_list(
		[ {Koma, Coord} || {Coord, Koma} <- dict:to_list(Board) ]
	),
	NextRevBoards = lists:filter(
		fun is_valid/1,
		lists:append(
			lists:map(
				fun(Koma) ->
					{X, Y} = dict:fetch(Koma, RevBoard),
					[
						dict:store(Koma, {X-1, Y}, RevBoard),
						dict:store(Koma, {X+1, Y}, RevBoard),
						dict:store(Koma, {X, Y-1}, RevBoard),
						dict:store(Koma, {X, Y+1}, RevBoard)
					]
				end,
				dict:fetch_keys(RevBoard)
			)
		)
	),
	lists:map(
		fun(B) ->
			dict:from_list( [
				{Coord, Koma}
				||
				{Koma, Coord} <- dict:to_list(B)
			])
		end,
		NextRevBoards
	).


is_valid(Board) ->
	is_valid(dict:to_list(Board), gb_sets:new()).

is_valid([], _) ->
	true;

is_valid([{Type, {X, Y}}|T], Set)
when
	Type =:= musume
->
	if
		X < 0 -> false;
		X+1 > 3 -> false;
		Y < 0 -> false;
		Y+1 > 4 -> false;
		true ->
			case
				gb_sets:is_element({X, Y}, Set) or
				gb_sets:is_element({X, Y+1}, Set) or
				gb_sets:is_element({X+1, Y}, Set) or
				gb_sets:is_element({X+1, Y+1}, Set)
			of
				true -> false;
				false ->
					is_valid(
						T,
						gb_sets:add_element({X, Y},
						gb_sets:add_element({X, Y+1},
						gb_sets:add_element({X+1, Y},
						gb_sets:add_element({X+1, Y+1}, Set))))
					)
			end
	end;

is_valid([{Type, {X, Y}}|T], Set)
when
		Type =:= chichi;
		Type =:= haha;
		Type =:= sofu;
		Type =:= sobo
->
	if
		X < 0 -> false;
		X > 3 -> false;
		Y < 0 -> false;
		Y+1 > 4 -> false;
		true ->
			case
				gb_sets:is_element({X, Y}, Set) or
				gb_sets:is_element({X, Y+1}, Set)
			of
				true -> false;
				false ->
					is_valid(
						T,
						gb_sets:add_element({X, Y},
						gb_sets:add_element({X, Y+1}, Set))
					)
			end
	end;

is_valid([{Type, {X, Y}}|T], Set)
when
		Type =:= kyodai
->
	if
		X < 0 -> false;
		X+1 > 3 -> false;
		Y < 0 -> false;
		Y > 4 -> false;
		true ->
			case
				gb_sets:is_element({X, Y}, Set) or
				gb_sets:is_element({X+1, Y}, Set)
			of
				true -> false;
				false ->
					is_valid(
						T,
						gb_sets:add_element({X, Y},
						gb_sets:add_element({X+1, Y}, Set))
					)
			end
	end;

is_valid([{_, {X, Y}}|T], Set)
->
	if
		X < 0 -> false;
		X > 3 -> false;
		Y < 0 -> false;
		Y > 4 -> false;
		true ->
			case
				gb_sets:is_element({X, Y}, Set)
			of
				true -> false;
				false ->
					is_valid(
						T,
						gb_sets:add_element({X, Y}, Set)
					)
			end
	end.



encode_board_by_type(Board) ->
	[
		komatype({0,0}, Board),
		komatype({1,0}, Board),
		komatype({2,0}, Board),
		komatype({3,0}, Board),
		komatype({0,1}, Board),
		komatype({1,1}, Board),
		komatype({2,1}, Board),
		komatype({3,1}, Board),
		komatype({0,2}, Board),
		komatype({1,2}, Board),
		komatype({2,2}, Board),
		komatype({3,2}, Board),
		komatype({0,3}, Board),
		komatype({1,3}, Board),
		komatype({2,3}, Board),
		komatype({3,3}, Board),
		komatype({0,4}, Board),
		komatype({1,4}, Board),
		komatype({2,4}, Board),
		komatype({3,4}, Board)
	].

komatype(Coord, Board) ->
	try dict:fetch(Coord, Board) of
		musume	-> $A;
		chichi	-> $B;
		haha	-> $B;
		sofu	-> $B;
		sobo	-> $B;
		kyodai	-> $F;
		kado	-> $G;
		sado	-> $G;
		wasai	-> $G;
		shodo	-> $G 
	catch
		_:_		-> $Z
	end.


print_path(_, []) -> ok;
print_path(N, [H|T]) ->
	io:format("--[~p]--~n", [N]),
	print_board(H),
	print_path(N+1, T).


print_board(Board) ->
	Layout = dict:from_list(layout(dict:to_list(Board), [])),
	io:format(" ~c ~c ~c ~c~n", [
			koma({0,0}, Layout),
			koma({1,0}, Layout),
			koma({2,0}, Layout),
			koma({3,0}, Layout)
		]),
	io:format(" ~c ~c ~c ~c~n", [
			koma({0,1}, Layout),
			koma({1,1}, Layout),
			koma({2,1}, Layout),
			koma({3,1}, Layout)
		]),
	io:format(" ~c ~c ~c ~c~n", [
			koma({0,2}, Layout),
			koma({1,2}, Layout),
			koma({2,2}, Layout),
			koma({3,2}, Layout)
		]),
	io:format(" ~c ~c ~c ~c~n", [
			koma({0,3}, Layout),
			koma({1,3}, Layout),
			koma({2,3}, Layout),
			koma({3,3}, Layout)
		]),
	io:format(" ~c ~c ~c ~c~n", [
			koma({0,4}, Layout),
			koma({1,4}, Layout),
			koma({2,4}, Layout),
			koma({3,4}, Layout)
		]),
	io:format("~n").

koma(Coord, Board) ->
	try dict:fetch(Coord, Board) of
		musume	-> $A;
		chichi	-> $B;
		haha	-> $C;
		sofu	-> $D;
		sobo	-> $E;
		kyodai	-> $F;
		kado	-> $G;
		sado	-> $H;
		wasai	-> $I;
		shodo	-> $J
	catch
		_:_		-> $ 
	end.


layout([], Layout) ->
	Layout;
layout([{{X, Y}, Type}|T], Layout)
when
	Type =:= musume
->
	layout(T, [
		{{X, Y},   Type}, {{X+1, Y},   Type},
		{{X, Y+1}, Type}, {{X+1, Y+1}, Type}
		| Layout
	]);
layout([{{X, Y}, Type}|T], Layout)
when
	Type =:= chichi;
	Type =:= haha;
	Type =:= sofu;
	Type =:= sobo
->
	layout(T, [
		{{X, Y},   Type},
		{{X, Y+1}, Type}
		| Layout
	]);
layout([{{X, Y}, Type}|T], Layout)
when
	Type =:= kyodai
->
	layout(T, [
		{{X, Y},   Type}, {{X+1, Y},   Type}
		| Layout
	]);
layout([{{X, Y}, Type}|T], Layout)
->
	layout(T, [
		{{X, Y},   Type}
		| Layout
	]).
